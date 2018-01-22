package point.of.sale;

public class ArtR56Display implements Display {

	ArtR56 artR56;
	public ArtR56Display(ArtR56 artR56) {
		this.artR56 = artR56;
	}

	public void showLine(String barcode) {
		artR56.showLine(barcode);
		
	}

}
