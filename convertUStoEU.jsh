class Product {

	long prodId;
	String brand;
	String prodType;
	String prodModel;
	double price;

	Product(long prodId, String brand, String prodType, String prodModel, double price) {
		this.prodId = prodId;
		this.brand = brand;
		this.prodType = prodType;
		this.prodModel = prodModel;
		this.price = price;
	}

	public String toString() {
		return String.format("ID: %d, Brand: %s, Type: %s, Model: %s, Price: %f ",
							prodId, brand, prodType, prodModel, price);
	}
}

String filePath = "/Users/juan/code/skillsoft/java-novice-to-javanista/track-1-java-novice/interactive-java-and-jshell-writing-java-programs-with-the-interactive-jshell/products.csv";
String outFilePath = "/Users/juan/code/skillsoft/java-novice-to-javanista/track-1-java-novice/interactive-java-and-jshell-writing-java-programs-with-the-interactive-jshell/eu_products.csv";
double EUR_USD = 0.83;

BufferedReader reader = new BufferedReader(new FileReader(filePath));

String line = "";

ArrayList<Product> prodArrayList = new ArrayList<Product>();

while((line=reader.readLine()) != null){

	String[] prod = null;

	try {
		prod = line.trim().split(",");

		prodArrayList.add(new Product(Long.parseLong(prod[0]), prod[1].trim(), 
								prod[2].trim(), prod[3].trim(),
								Double.parseDouble(prod[4])));
	}
	catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
		System.out.format("Exception: %s found. \nSkipping this product: %s\n", 
							e.getClass(), Arrays.toString(prod));
		continue;
	}
}

Iterator<Product> iterator = prodArrayList.iterator();
BufferedWriter writer = new BufferedWriter(new FileWriter(outFilePath));

while(iterator.hasNext()) {

	Product prod = iterator.next();

	String euModel = "EU-" + prod.prodModel;
	double euPrice = Math.round(EUR_USD*prod.price*100) / 100.0;

	writer.write(prod.prodId + "," + prod.brand + "," + prod.prodType + "," 
				+ euModel + "," + euPrice + "\n");
}

writer.close();

System.out.println("The program is done!");

/exit