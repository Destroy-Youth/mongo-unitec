package unitec.org.mongounitec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
Aplicaación:
https://lihe-mongounitec.herokuapp.com/api/mensajito
*/







@SpringBootApplication
public class MongounitecApplication implements CommandLineRunner{

    @Autowired RepositorioMensajito repoMensa;
    
	public static void main(String[] args) {
		SpringApplication.run(MongounitecApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
	    
        Mensajito mensa = new Mensajito("Primero mongo", "mi primer mongo");
       // repoMensa.save(mensa);
            System.out.println("Mensaje guardado");
	    
    }
}
