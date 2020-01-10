package cgm.pages;

import java.io.IOException;

import org.openqa.selenium.By;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import control.elementos.ObjetosConfigAux;

public class Login {

	ObjetosConfigAux objAux;
	
	By txtuser = By.xpath("//*[@id=\"usuario\"]");
	By txtpassword = By.xpath("//*[@id=\"pass\"]");
	By btningresar = By.xpath("//*[@id=\"frmLogin\"]/div[4]/button");
	
//constructor

	public Login(ObjetosConfigAux objAux) {
	this.objAux = objAux;
}
	


//Evento

public void txtUser() {
	
	objAux.getDriver().findElement(txtuser).sendKeys("ESBAPP2");
	
}

	public void txtPassword() {
	objAux.getDriver().findElement(txtpassword).sendKeys("Redhat2019");
	
}
	public void btningresa() {
		objAux.getDriver().findElement(btningresar).click();
		
	}

	//Crear metodo
	
	public void Ingresar () throws IOException {
		
	objAux.getDriver().switchTo();
	txtUser();
	txtPassword ();
	objAux.AdminDocPdf.generaEvidencia("Ingreso Login", Shutterbug.shootPage(objAux.getDriver()).getImage());
	btningresa();
		
	}

}