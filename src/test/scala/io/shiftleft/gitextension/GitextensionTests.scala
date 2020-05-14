package io.shiftleft.gitextension

import io.shiftleft.GitExtensionOpts
import io.shiftleft.gitextension.{Gitextension, GitExtensionOpts}
import io.shiftleft.semanticcpg.layers.LayerCreatorContext
import io.shiftleft.semanticcpg.testfixtures.CodeToCpgFixture
import org.scalatest.{Matchers, WordSpec}

class GitextensionTests extends WordSpec with Matchers {

  private val code =
    """
      |int main(int argc, char **argv) {
      |
      |}
      |""".stripMargin

  CodeToCpgFixture(code) { cpg =>
    val context = new LayerCreatorContext(cpg)
    val options = new GitExtensionOpts("")
    new Gitextension(options).create(context)

    "should do something useful" in {}

  }

}
