
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Movie {
	
	static float success;
	static float verifySuccess;
	static float noAnyLangSource;		
	
	private int id;
	private String vikiURL_TR;
	private String wikiURL_EN;
	private String year;
	private InfoBox infoBox;
	private boolean verified;
	
	//CONSTRUCTORS
	public Movie(){
		setVikiURL_TR("TÜRKÇE KAYNAK BULUNAMADI");//türkçe kaynak bulunamazsa bu deðer böyle kalacaktýr.
	}
	public Movie(int id, String wikiURL_EN, String vikiURL_TR, String year){
		setId(id);
		setWikiURL_EN(wikiURL_EN);
		setVikiURL_TR(vikiURL_TR);
		setYear(year);
	}	
	
	//FUNCTIONS
	public void setActiveVikiURL(){
		/*
		 * setActiveWikiLink() methodu çalýþtýktan sonra elimizde movienin eriþilebilir ve doðru inglizce linki var
		 * 404 hatasý yani exception gelme ihtimali yok. Bu link jsoup ile parse edilip türkçe link için kontrol edilir
		 * yaratýlýþta movie nesneleri new Movie() constructorýnda vikiURL_TR olarak "TÜRKÇE KAYNAK BULUNAMADI" deðerini alýr
		 * method içinde bu deðerin deðiþmesini bekleriz, eðer deðiþmiþse success 1 atar.
		 */
		Document doc = null;
		try {
			
			doc = Jsoup.connect(this.getWikiURL_EN()).get();
			Elements links = doc.select("a[href*=https://tr.");//https://tr. içeren a-href'leri bul
	    	for(Element element : links){
	    		this.setVikiURL_TR(element.attr("href"));//zaten 1 tane gelicek vikiURL'ye kaydet
	    			
	    	}
	    	if(!this.getVikiURL_TR().equals("TÜRKÇE KAYNAK BULUNAMADI"))
	    		setSuccess(getSuccess() + 1);
		} catch (Exception e) {
			
		}    	
	}
	public void setActiveWikiLink(){
		/*
		 * _(YIL_film), _(film) ve uzantýsýz linkleri 404 hatasý almayana kadar dener, 
		 * eriþilebilen linki uzantýsyla birlikte movie'nin wikiURL_EN fieldýna set eder.
		 */
		FileIO fileIO = FileIO.getFileIO();
		String activeLink = null;
		System.out.println(this.id+")"+this.wikiURL_EN);
		if(fileIO.check404(this.wikiURL_EN+"_("+this.year+"_film)")){
			activeLink = this.wikiURL_EN+"_("+this.year+"_film)";
		}
		else if(fileIO.check404(this.wikiURL_EN+"_(film)")){
			activeLink = this.wikiURL_EN+"_(film)";
		}
		else if(fileIO.check404(this.wikiURL_EN)){
			activeLink = this.wikiURL_EN;
		}
		else {
			activeLink = "INGLIZCE KAYNAK BULUNAMADI";
			setNoAnyLangSource(getNoAnyLangSource() + 1);
		}
		System.out.println(" *active URL: "+activeLink);
		this.setWikiURL_EN(activeLink);
	}
	public void print(){
		System.out.println("\n"+this.id+")"+this.infoBox.getTitle()+"("+this.year+")");
		this.infoBox.printInfo();
		
		System.out.println("Wiki EN: "+this.wikiURL_EN+"\nViki TR: "+this.vikiURL_TR);
		
		//System.out.println("Verified: "+this.getVerified());
	}
	
	//GETTER-STTER METHODLARI
	public InfoBox getInfoBox() {
		return infoBox;
	}
	public void setInfoBox(InfoBox infoBox) {
		this.infoBox = infoBox;
	}	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVikiURL_TR() {
		return vikiURL_TR;
	}
	public void setVikiURL_TR(String vikiURL_TR) {
		this.vikiURL_TR = vikiURL_TR;
	}
	public String getWikiURL_EN() {
		return wikiURL_EN;
	}
	public void setWikiURL_EN(String wikiURL_EN) {
		this.wikiURL_EN = wikiURL_EN;
	}
	public float getSuccess() {
		return success;
	}
	public void setSuccess(float success) {
		Movie.success = success;
	}
	public float getNoAnyLangSource() {
		return noAnyLangSource;
	}
	public void setNoAnyLangSource(float noAnyLangSource) {
		Movie.noAnyLangSource = noAnyLangSource;
	}
	public void setVerified(boolean verified){
		this.verified = verified;
	}
	public boolean getVerified(){
		return this.verified;
	}
	public float getVerifySuccess() {
		return verifySuccess;
	}
	public void setVerifySuccess(float verifySuccess) {
		Movie.verifySuccess = verifySuccess;
	}
	
	
}

