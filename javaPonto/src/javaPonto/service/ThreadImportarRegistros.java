package javaPonto.service;

import java.util.Date;



import javaPonto.dao.DaoPonto;

public class ThreadImportarRegistros implements Runnable{

	
	
	@Override
	public void run() {

		ImportarService importarService = new ImportarService();
		while(true) {
			System.out.println("Iniciando :"+new Date());
			importarService.importarRegistrosPonto();
			System.out.println("Findando  :"+new Date());
			try {
				Thread.sleep(1000*60*10);
			} catch (Exception e) {
				DaoPonto.escreverLog(e, "COLOCANDO A THREAD PARA DORMIR");
			}
		}
	}
	
}
