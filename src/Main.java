import java.io.IOException;



public class Main {

	public static void main(String[] args) throws IOException {
		/*
		 * Arhieve ve FileIO singleton olduðu için yeni nesne yaratýyorum
		 * sýnýfýn içinde olan static nesneyi alýp kullanýyoruz
		 */
		Archive archieve = Archive.getArchive();
		FileIO fileIO = FileIO.getFileIO();
		
		archieve.getMovies(fileIO.fileToString("top250"));
		archieve.checkAndPrintMovies(fileIO.fileToString("top250_info"));
		
		System.out.println(
				"\nIncelenen Film Sayýsý= "+archieve.movieArchive.size()
				+ "\nIngilizce Kaynak Bulunan Film Sayýsý= "+(archieve.movieArchive.size()-(int)new Movie().getNoAnyLangSource())
				+ "\nIngilizce ve Türkçe Kaynak Bulunan Film sayýsý= "+(int)new Movie().getSuccess()
				+ "\nTR Link Baþarý Oraný= %"+(new Movie().getSuccess()*100)/archieve.movieArchive.size()
				+ "\nTR Link Onaylanma Oraný= %"+(new Movie().getVerifySuccess()*100)/new Movie().getSuccess());
		
		
	}
	
}
