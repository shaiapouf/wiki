
import java.io.IOException;
import java.util.ArrayList;

public class Archive {
	
	/*
	 * Singleton Class
	 */
	private static Archive  archive   = new Archive();	
	private Archive(){
		
	}	
	public static Archive getArchive() {
		return archive;
	}
	
	ArrayList<Movie> movieArchive = new ArrayList<Movie>();
	
	//movieArchive'deki tüm movilerin datasýný ekrana basar
	public void list(){
		System.out.println("------------------------------------------------------------------------");
		for( Movie item : movieArchive){
			System.out.println((item.getId())+")"
								+item.getTitle()+"("+item.getYear()+")"+", "
								+item.getWikiURL_EN()+", "
								+item.getVikiURL_TR());
			item.printInfoBOX();
		}
	}
	/*
	 * dosya içeriðini çevirdiðimiz stringi | 'a göre ayýrýp fieldlarý doldurduðumuz
	 * ve sonra movieArchieve'e eklediðimiz method
	 * burada önce wikiLink alýnýr sonra bu linkten aktif ve doðru wikiLink bulunur
	 * bu aktif wikiLink'ten vikiLink_TR çýkarýlýr. yine bu aktif wikiLink'ten infoBox
	 * deðerleri alýnýr. Yýl, id de bulunduktan sonra
	 * movie nesnesine bu deðerler verilir(constructor çaðýrdýðýmýzda bu deðerler veriliyor)
	 * 
	 */
	
	public void collect(String content) throws IOException{
		String[] blocks = content.split("\\||\\n");		
		
		
		for(int i=0; i<blocks.length; i++){	
			if(i%5 == 1){//her bir yeni title'da
				String title = null;				
				Movie movie = new Movie();
				
				String linkExtension = blocks[i];
				
				String[] titleParts = linkExtension.split("_");
				for(int j=0; j<titleParts.length; j++){
					if(title==null){
						title=titleParts[j];
		        	 }else title += " "+titleParts[j];
				}//tile oluþturuldu
				movie.setTitle(title);	//title set edildi					
				String year = blocks[i+1];//link extensiondan bir sonraki parça year				
				movie.setYear(year); //year set edildi
				movie.setId(movieArchive.size()+1);							
				movie.setWikiURL_EN("https://en.wikipedia.org/wiki/"+linkExtension);//baþlangýç EN wiki link
				movie.setActiveWikiLink();
				movie.setActiveVikiURL();
				movie.setInfoBox(new InfoBox(movie.getWikiURL_EN()));					
				movieArchive.add(movie);
			}			
		}		
	}
	
	public void collectInfoBox(String content) throws IOException{
		
		String[] blocks = content.split("\\||\\n");		
				
		for(int i=0; i<blocks.length; i++){
			
			switch(i%7){
				case 1: 
					String title = null;
					
					String[] titleParts = blocks[i].split("_");
					for(int j=0; j<titleParts.length; j++){
						if(title==null){
							title=titleParts[j];
						}else title += " "+titleParts[j];
					}
					System.out.println("t: "+title);
					break;
				case 2:
					String director = blocks[i];
					System.out.println("d: "+director);
					break;
				case 3:
					String[] actors = blocks[i].split(",");
					for(int j=0; j< actors.length; j++){
						//actor ekle
						if(j!=0){
							actors[j]=actors[j].substring(1, actors[j].length());
						}
						
						System.out.println("a: "+actors[j]);
					}
					break;
				case 4:
					String[] genre = blocks[i].split(",");
					for(int j=0; j< genre.length; j++){
						//genre ekle
						if(j!=0){
							genre[j]=genre[j].substring(1, genre[j].length());
						}
						System.out.println("g: "+genre[j]);
					}
					break;
				case 5:
					String imdbRating = blocks[i];
					System.out.println("imdb: "+imdbRating);
					break;
				case 6:
					String tomatoRating = blocks[i];
					System.out.println("tomtom: "+tomatoRating);
					break;
			
			}
			//infobox'un bilgileri switch-case'den gelenlerle doldurulur
			//movie.setInfoBox(infoBox);
		}

}
}
