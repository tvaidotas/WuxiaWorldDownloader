import java.io._
import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.selenium.WebBrowser

object Main extends App with WebBrowser  {
  implicit val webDriver: WebDriver = new HtmlUnitDriver
  val pw = new PrintWriter(new File("hello.txt" ))
  for(i <- 637 to 638) {
    webDriver.get("https://www.wuxiaworld.com/novel/emperors-domination/emperor-chapter-" + i)
    Thread.sleep(400)
    val content = webDriver.findElements(By.tagName("p"))
    content.forEach(e => {
      pw.write(e.getText + '\n')
      pw.println()
    })
  }
  pw.close
}
