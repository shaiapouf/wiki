import java.io.IOException;
import java.util.ArrayList;



public class Main {

	public static void main(String[] args) throws IOException {
		/*
		 * Arhieve ve FileIO singleton olduğu için yeni nesne yaratıyorum
		 * sınıfın içinde olan static nesneyi alıp kullanıyoruz
		 */
		Archive archieve = Archive.getArchive();
		FileIO fileIO = FileIO.getFileIO();
		
		archieve.getMovies(fileIO.fileToString("top250"));
		archieve.checkAndPrintMovies(fileIO.fileToString("top250_info"));
		report();
		
		
		
	}
	public static void report(){
		Archive archieve = Archive.getArchive();
		ArrayList<Movie> movieArchive = archieve.getMovieArchive();
		System.out.println(
				"\nIncelenen Film Sayısı= "+movieArchive.size()
				+ "\nIngilizce Kaynak Bulunan Film Sayısı= "+(int)(movieArchive.size()-Movie.noAnyLangSource)
				+ "\nIngilizce ve Türkçe Kaynak Bulunan Film sayısı= "+(int)Movie.success
				+ "\nTR Link Başarı Oranı= %"+(Movie.success*100)/movieArchive.size()
				+ "\nTR Link Onaylanma Oranı= %"+(Movie.verifySuccess*100)/new Movie().getSuccess());
	}
	
}


// GTA 5 CODEX FPS FIX PATCH 0.39
