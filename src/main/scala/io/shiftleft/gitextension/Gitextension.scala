package io.shiftleft.gitextension

import better.files._
import io.shiftleft.SerializedCpg
import io.shiftleft.codepropertygraph.Cpg
import io.shiftleft.passes.{CpgPass, DiffGraph}
import io.shiftleft.semanticcpg.Overlays
import io.shiftleft.semanticcpg.layers.{LayerCreator, LayerCreatorContext, LayerCreatorOptions}
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import io.shiftleft.semanticcpg.language._

object Gitextension {

  /**
   * This is the extensions official name as will be shown in the table
   * one obtains when running `run` on the Ocular shell.
   * */
  val overlayName = "My extension"

  /**
   * A short description to be shown in the table obtained when
   * running `run` on the Ocular shell.
   * */
  val description = "My description"

  /**
   * Option object initialize to defaults. This object will be made
   * accessible to the user via `opts.myextension`.
   * */
  def defaultOpts = GitExtensionOpts(".")
}

/**
 * Options can be passed to the extension via a custom options
 * class that derives from `LayerCreatorOptions`. In our example,
 * we use the option class below to hand the path to the git
 * repository from the user to the extension.
 * */
case class GitExtensionOpts(var pathToRepo : String) extends LayerCreatorOptions {}

class Gitextension(options : GitExtensionOpts) extends LayerCreator {
  override val overlayName: String = Gitextension.overlayName
  override val description: String = Gitextension.description

  /**
   * This method is executed when the user issues the command
   * `run.myextension`.
   * */
  override def create(context: LayerCreatorContext,
                      serializeInverse: Boolean): Unit = {

    val cpg = context.cpg
    val pathToRepo = options.pathToRepo
    println("pathToRepo: ", pathToRepo)

    /**
     * A bit of demo code to show that we can now interface with external
     * JVM-based libraries
     * */
    val builder = new FileRepositoryBuilder()
    val repository = builder.setGitDir(File(pathToRepo).toJava)

    /**
     * We can query the graph as well
     * */

    println("Printing list of methods detected:")
    cpg.method.name.foreach(println)

    /** Now, let's modify the graph in a pass */
    val serializedCpg = context.outputDir.map(dir => new SerializedCpg(dir)).getOrElse(new SerializedCpg())
    new MyPass(cpg).createApplySerializeAndStore(serializedCpg, serializeInverse)
    serializedCpg.close()

  }

  private class MyPass(cpg : Cpg) extends CpgPass(cpg) {
    override def run(): Iterator[DiffGraph] = {
      implicit val diffGraph: DiffGraph.Builder = DiffGraph.newBuilder
      // You can modify the graph here
      cpg.method.name(".*main.*").newTagNode("RECENTLY_CHANGED").store
      Iterator(diffGraph.build)
    }
  }

  /**
   * This method may be implemented to check whether the extension has
   * already been run by inspecting the graph. This method is only
   * relevant for legacy CPGs. You can set it to false on new extensions.
   * */
  override def probe(cpg: Cpg): Boolean = false
}
