package cgm.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.assertthat.selenium_shutterbug.core.Shutterbug;

import control.elementos.ObjetosConfigAux;

public class Crear {

	ObjetosConfigAux objAux;

	// Creacion de Elementos

	By btnSalidas = By.xpath("//*[@id='btnSalidas']");
	By btnCargaMasiva = By
			.xpath("//a[@class='ng-binding ng-scope' and contains(.,'Ejecución programada Carga Masiva')]");
	By btnNuevo = By.xpath("//div[@class='row']/button");

	// Datos de la tarea

	By txtNTarea = By.xpath("//*[@id='nombre-tarea']");
	By btnCron = By.xpath("//*[@id='btnPopOver']");
	By btnMinutos = By.xpath("//*[@id='MinutesInput']/option [5]");
	By btnGenerarcron = By.xpath("//*[@id=\"btnGenerate\"]");
	By btnCerrar = By.xpath("//*[@id=\"btnClosePopover\"]");
	By bntEstado = By.xpath("//*[@ng-model='nuevo.estado']/option[@value='true']");
	By btnProceso = By
			.xpath("//*[@id=\"nuevo-automatico\"]/div/div/form/div[1]/div/div/div[2]/div[2]/span/div/div/button");
	By txtEscogerProceso = By.xpath("(//*[@class='dropdown-toggle ng-binding btn btn-default'])[2]");
	By btnVariacionFecha = By.xpath("//*[@id=\"select-tipo-variacion\"]/option [5]");

	By btnGuardar = By.xpath("//*[@ng-click='guardar()']");

	// Creacion del constructor

	public Crear(ObjetosConfigAux objAux) {
		this.objAux = objAux;
	}

	// Creacion de eventos

	public void clicBtnSalidas() {
		objAux.getDriver().findElement(btnSalidas).click();
	}

	public void clicBtnCargaMasiva() throws InterruptedException {
		objAux.getDriver().findElement(btnCargaMasiva).click();
		Thread.sleep(3000);
	}

	public void clicBtnNuevo() {
		objAux.getDriver().findElement(btnNuevo).click();

	}

	public void selecProceso() {

		String pProceso = "CALC_TIES";

		objAux.getDriver().findElement(btnProceso).click();
		objAux.getDriver().findElement(By.xpath("//span[@class='ng-binding' and contains(.,'" + pProceso + "')]"))
				.click();
		objAux.getDriver().findElement(btnProceso).click();
	}

	public void Creaciontarea() throws InterruptedException, IOException {
		objAux.getDriver().findElement(txtNTarea).sendKeys("NUEVA_TAREA");
		objAux.getDriver().findElement(btnCron).click();
		objAux.getDriver().findElement(btnMinutos).click();
		objAux.getDriver().findElement(btnGenerarcron).click();
		objAux.getDriver().findElement(btnCerrar).click();
		objAux.getDriver().findElement(bntEstado).click();
		objAux.getDriver().findElement(btnVariacionFecha).click();

		Thread.sleep(2000);
		selecProceso();
		Thread.sleep(2000);
		objAux.AdminDocPdf.generaEvidencia("Datos de la tarea", Shutterbug.shootPage(objAux.getDriver()).getImage());
		objAux.getDriver().findElement(btnGuardar).click();

	}
	// Creacion de metodos

	public void crearCargaMasiva() throws IOException, InterruptedException {

		clicBtnSalidas();
		clicBtnCargaMasiva();
		clicBtnNuevo();
		Thread.sleep(2000);
		Creaciontarea();
		Thread.sleep(2000);
		objAux.AdminDocPdf.generaEvidencia("Creación tarea", Shutterbug.shootPage(objAux.getDriver()).getImage());
	}

}
