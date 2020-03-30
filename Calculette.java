import java.util.ArrayList;

public class Calculette implements Observable {
	
	private double operande1 = 0d;
	private double operande2 = 0d;
	private double resultat  = 0d;
	private String operateur = " ";
	
	private ArrayList<Observateur> listObservateur = new ArrayList<Observateur>();
	
	public double getOperande1() {
		return operande1;
	}
	
	public void setOperande1(double op1) {
		operande1 = op1;
	}
	
	public double getOperande2() {
		return operande2;
	}
	
	public void setOperande2(double op2) {
		operande2 = op2;
	}
	
	public String getOperateur() {
		return operateur;
	}
	
	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}
	
	public void effectuerCalcul() throws ArithmeticException {
		
		switch (operateur) {
		
		case "+" :
			resultat = operande1 + operande2;
			break;
			
		case "-" :
			resultat = operande1 - operande2;
			break;
			
		case "*" :
			resultat = operande1 * operande2;
			break;
			
		case "/" :
			if (operande2 == 0) {
				throw new ArithmeticException("Division par 0");
			}
			else {
				resultat = operande1 / operande2;
				break;
			}
		}
		this.updateObservateur(resultat);
	}

	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		this.listObservateur.add(obs);
	}

	@Override
	public void updateObservateur(double nombre) {
		// TODO Auto-generated method stub
		for (Observateur obs : this.listObservateur) {
			obs.update(Double.toString(nombre));
		}
	}

	@Override
	public void delObservateur() {
		// TODO Auto-generated method stub
		this.listObservateur = new ArrayList<Observateur>();
	}

}
