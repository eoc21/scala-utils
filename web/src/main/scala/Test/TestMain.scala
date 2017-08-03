import play.api.libs.json._
import web.EocHttpClient
/**
  * Created by edwardcannon on 03/08/2017.
  */
object TestMain {

  def main(args: Array[String]) {
    var http = new EocHttpClient()
    val data = http.getRestContent("https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/cid/2244/property/MolecularFormula/JSON",5000,5000)
    val json: JsValue = Json.parse(data)

    val properties = (json \ "PropertyTable" \ "Properties").as[JsArray]
    val cid = properties \\ "CID"
    val molecularFormula = properties \\ "MolecularFormula"
    println(molecularFormula)
  }
}
