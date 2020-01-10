package cgm.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.function.ObjLongConsumer;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.lowagie.text.DocumentException;

import cgm.pages.Crear;
import cgm.pages.Login;
import control.elementos.ObjetosConfigAux;
import evidencia.doc.pdf.AdminDocPdf;
import model.Ambientes;
import model.DispositivoPrueba;
import model.Estados;
import model.Navegadores;

public class Ejecucion {

	ObjetosConfigAux objAux;
	Crear objCrear;
	Login objLogin;
	
	@BeforeClass
	public void setUp() throws IOException, InterruptedException {

		objAux = new ObjetosConfigAux("2", 10);
	    objLogin = new Login(objAux);
		objCrear = new Crear(objAux);
		

	}

	@Test
	public void crearCargaMasiva() throws IOException, InterruptedException {

		objAux.AdminDocPdf = new AdminDocPdf(Ambientes.NA, Navegadores.CHROME, DispositivoPrueba.WEB);
		objLogin.Ingresar();
		objCrear.crearCargaMasiva();
	}

	@AfterMethod
	public void finalizeTest(ITestResult t) throws MalformedURLException, DocumentException, IOException {
		if (t.getStatus() == ITestResult.SUCCESS)
			objAux.AdminDocPdf.crearDocumento(Estados.SUCCESS);
		else {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			Throwable cause = t.getThrowable();
			if (null != cause) {
				cause.printStackTrace(pw);
				objAux.AdminDocPdf.generaEvidencia("Resultado NO Esperado: ",
						Shutterbug.shootPage(objAux.getDriver()).getImage());
			} else {
				objAux.AdminDocPdf.generaEvidencia("Resultado NO Esperado: ",
						Shutterbug.shootPage(objAux.getDriver()).getImage());
			}
			objAux.AdminDocPdf.crearDocumento(Estados.FAILED);
		}
	}

	@AfterClass
	public void exit() {

		objAux.getDriver().quit();
	}

}
