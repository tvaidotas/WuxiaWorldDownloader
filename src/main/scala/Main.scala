import java.io._

import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.{By, WebDriver}
import org.scalatest.selenium.WebBrowser

object Main extends App with WebBrowser  {

  val webDriver: WebDriver = getWebDriver

  val url = "https://www.wuxiaworld.com/novel/emperors-domination/emperor-chapter-"

  getChapters(1, 1, url)

  def getChapters(chapterStartNumber: Int, chapterEndNumber: Int, url: String): Unit = {
    for(index <- chapterStartNumber to chapterEndNumber) {
      val pw = getPrintWriter(s"emperor-chapter$index-.txt" )
      webDriver.get(url + index)
      Thread.sleep(1000)
      val content = webDriver.findElements(By.tagName("p"))

      val details = getDetails(webDriver)
      pw.write(details.title + '\n' + '\n')
      pw.write(details.chapter + '\n' + '\n')

      content.forEach(e => {
        pw.write(e.getText + '\n')
        pw.println()
      })
      closePrintWrite(pw)
    }
  }

  def getDetails(webDriver: WebDriver): Names = {
    val h4 = webDriver.findElements(By.tagName("h4"))
    Names(h4.get(0).getText, h4.get(1).getText)
  }

  def getWebDriver: WebDriver ={
    new HtmlUnitDriver
  }

  def getPrintWriter(fileName: String): PrintWriter = {
    new PrintWriter(new File(fileName ))
  }

  def closePrintWrite(printWriter: PrintWriter): Unit = {
    printWriter.close()
  }


}
