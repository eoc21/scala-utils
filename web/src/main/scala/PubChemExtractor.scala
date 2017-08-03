package web
import play.api.libs.json.{JsArray, JsValue, Json}

/**
  * Created by edwardcannon on 03/08/2017.
  * Extracts data from PubChem DataBase
  */
class PubChemExtractor {
  val httpClient = new EocHttpClient()

  /**
    * Extracts chemical formula given chemical id
    * @param chemicalID - Pubchem chemical ID
    * @return
    */
  def extractChemicalFormula(chemicalID: String): String = {
    val BASEURL = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/cid/"
    val data = this.httpClient.getRestContent(BASEURL+chemicalID+"/property/MolecularFormula/JSON", 5000, 5000)
    val json: JsValue = Json.parse(data)
    val properties = (json \ "PropertyTable" \ "Properties").as[JsArray]
    val molecularFormula = properties \\ "MolecularFormula"
    molecularFormula.mkString("")
  }
}
