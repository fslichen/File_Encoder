package evolution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Application {
	public byte encode(byte b) {
		if (b == 127) {
			return -128;
		}
		return new Byte(b + 1 + "");
	}
	
	public byte decode(byte b) {
		if (b == -128) {
			return 127;
		}
		return new Byte(b - 1 + "");
	}
	
	public void encode(Path sourceFile, Path targetFile) throws IOException {
		byte[] bytes = Files.readAllBytes(sourceFile);
		byte[] encodedBytes = new byte[bytes.length];
		for (int i = 0; i < bytes.length; i++) {
			encodedBytes[i] = encode(bytes[i]);
		}
		Files.write(targetFile, encodedBytes);
	}
	
	public void decode(Path sourceFile, Path targetFile) throws IOException {
		byte[] encodedBytes = Files.readAllBytes(sourceFile);
		byte[] bytes = new byte[encodedBytes.length];
		for (int i = 0; i < encodedBytes.length; i++) {
			bytes[i] = decode(encodedBytes[i]);
		}
		Files.write(targetFile, bytes);
	}
	
	public static void main(String[] args) throws IOException {
		Application app = new Application();
		app.encode(Paths.get("/Users/chenli/Desktop/hello.txt"), Paths.get("/Users/chenli/Desktop/world.txt"));
		app.decode(Paths.get("/Users/chenli/Desktop/world.txt"), Paths.get("/Users/chenli/Desktop/this.txt"));
	}
}
