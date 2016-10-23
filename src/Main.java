import java.io.IOException;



public class Main {

	public static void main(String[] args) throws IOException {
		//github second oommit check	
		//another change for github
		/*
		 * Arhieve ve FileIO singleton olduðu için yeni nesne yaratýyorum
		 * sýnýfýn içinde olan static nesneyi alýp kullanýyoruz
		 */
		Archive archieve = Archive.getArchive();
		FileIO fileIO = FileIO.getFileIO();
		
		archieve.collect(fileIO.fileToString("top250"));
		archieve.list();
		System.out.println(
				"\nIncelenen Film Sayýsý= "+archieve.movieArchive.size()
				+ "\nIngilizce Kaynak Bulunan Film Sayýsý= "+(archieve.movieArchive.size()-(int)new Movie().getNoAnyLangSource())
				+ "\nIngilizce ve Türkçe Kaynak Bulunan Film sayýsý= "+(int)new Movie().getSuccess()
				+ "\nBaþarý Oraný= %"+(new Movie().getSuccess()*100)/archieve.movieArchive.size());
		
		//this is the 3rd commit for github
		//last commit fot github(diff color)
		//InfoBox i = new InfoBox("https://en.wikipedia.org/wiki/Pulp_Fiction");
			
		/*Archive archieve = Archive.getArchive();
		FileIO fileIO = FileIO.getFileIO();
		archieve.collectInfoBox(fileIO.fileToString("top250_info"));*/
		
	}
	
}
