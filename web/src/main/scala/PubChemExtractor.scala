package web
import play.api.libs.json.{JsArray, JsValue, Json}

object PubChemBaseURLs {
  val COMPOUND_ID_BASE_URL = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/cid/"
  val COMPOUND_NAME_BASE_URL = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/"
}

/**
  * Created by edwardcannon on 03/08/2017.
  * Extracts data from PubChem DataBase
  */
class PubChemExtractor {
  val httpClient = new EocHttpClient()

  /**
    * Extracts chemical formula given chemical id
    *
    * @param chemicalID - Pubchem chemical ID
    * @return
    */
  def extractChemicalFormulaFromName(chemicalID: String): String = {
    val chemical_name = chemicalID.replaceAll("\\s", "")
    val data = this.httpClient.getRestContent(PubChemBaseURLs.COMPOUND_NAME_BASE_URL+chemical_name+"/property/MolecularFormula/JSON",
      5000, 5000)
    val json: JsValue = Json.parse(data)
    val properties = (json \ "PropertyTable" \ "Properties").as[JsArray]
    val molecularFormula = properties \\ "MolecularFormula"
    molecularFormula.mkString("")
  }

  /**
    * Extracts molecular properties as JSON object
    * @param chemicalID - Chemical input name
    * @return
    */
  def extractAllProperties(chemicalID: String): play.api.libs.json.JsValue = {
    val chemical_name = chemicalID.replaceAll("\\s", "")
    val allProperties = "MolecularFormula,MolecularWeight,CanonicalSMILES,IsomericSMILES," +
      "InChI,InChIKey,IUPACName,XLogP,ExactMass,MonoisotopicMass,TPSA,Complexity,Charge,"+
      "HBondDonorCount,HBondAcceptorCount,RotatableBondCount,HeavyAtomCount,IsotopeAtomCount," +
      "AtomStereoCount,BondStereoCount," +
      "Volume3D,XStericQuadrupole3D," +
      "Fingerprint2D"
    val url = PubChemBaseURLs.COMPOUND_NAME_BASE_URL+chemical_name+"/JSON"
    try {
      val data = this.httpClient.getRestContent(url,
        5000, 5000)
      val json: JsValue = Json.parse(data)
      json
    }
    catch {
      case e: Exception => {
        println("Error!")
        return Json.parse("")
      }
    }
  }


}
