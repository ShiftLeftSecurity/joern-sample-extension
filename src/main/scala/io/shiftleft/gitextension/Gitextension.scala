package io.shiftleft.gitextension

import better.files._
import io.shiftleft.codepropertygraph.Cpg
import io.shiftleft.semanticcpg.layers.{LayerCreator, LayerCreatorContext, LayerCreatorOptions}
import org.eclipse.jgit.storage.file.FileRepositoryBuilder

object Gitextension {
  val overlayName = "My extension"
  val description = "My description"

  def defaultOpts = GitExtensionOpts("")
}

case class GitExtensionOpts(var pathToRepo : String) extends LayerCreatorOptions {}

class Gitextension(options : GitExtensionOpts) extends LayerCreator {
  override val overlayName: String = Gitextension.overlayName
  override val description: String = Gitextension.description

  override def create(context: LayerCreatorContext,
                      serializeInverse: Boolean): Unit = {

    if (areOptionsValid) {
      val pathToRepo = options.pathToRepo
      val builder = new FileRepositoryBuilder()
      val repository = builder.setGitDir(File(pathToRepo).toJava)

    }

  }

  private def areOptionsValid: Boolean = {
    val pathToRepo = options.pathToRepo
    val projectDir = File(pathToRepo)
    val isValid = (projectDir / ".git ").exists
    if (!isValid) {
      System.err.println("Invalid path to repo at `opts.joerngitextension.pathToRepo`")
      System.err.println("The directory you specify must contain a `.git` subdirectory")
    }
    isValid
  }



  override def probe(cpg: Cpg): Boolean = false
}
