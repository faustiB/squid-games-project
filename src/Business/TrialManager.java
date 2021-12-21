package Business;

public class TrialManager {


    private static final int ARTICLE = 1;
    private static final int MASTER = 2;
    private static final int THESIS = 3;
    private static final int BUDGET = 4;



    public void createTrial(int trialType) {
        switch (trialType){
            case ARTICLE -> createArticleTrial();
            case MASTER -> createMasterTrial();
            case THESIS -> createThesisTrial();
            case BUDGET -> createBudgetTrial();


        }
    }

    //TODO: Crear los métodos de mostrar los diferentes input ( figura 10 en el enunciado ) y que en vez de void devuelva el producto? pensarlo.
    private void createArticleTrial() {
        System.out.println("KELOKE");
    }

    private void createMasterTrial() {
    }

    private void createThesisTrial() {
    }

    private void createBudgetTrial() {
    }

    //TODO: Implementar metodo y posibles cambios en el controler, puesto para compilar.
    public void listTrials() {
    }
    //TODO: Implementar metodo y posibles cambios en el controler, puesto para compilar.
    public void deleteTrial() {
    }
}
