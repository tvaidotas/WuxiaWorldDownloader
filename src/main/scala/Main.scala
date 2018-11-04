import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.scalatest.selenium.WebBrowser

object Main extends App with WebBrowser {

  implicit val webDriver: WebDriver = new HtmlUnitDriver

  val host = "https://www.wuxiaworld.com/"
  val novel = "/novel/emperors-domination/emperor-chapter-" //637

  val page = webDriver.get(host + novel + 637)

  println(webDriver.findElement(By.className("fr-view")).getText)

}
