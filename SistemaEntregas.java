public class SistemaEntregas {

    double quilometro = 0;
    double precoBase = 5.0;
    double precoPagar;
    String nome;

    SistemaEntregas(String n, double km) {
        this.nome = n; // Atribui o parâmetro 'n' à variável de instância 'nome'
        this.quilometro = km; // Atribui o parâmetro 'km' à variável de instância 'quilometro'

    }

    String Nome() {
        return nome;
    }

    void calcularFrete(double km) {
        if (km > 0) {
            precoPagar = precoBase + ( 2 * km);
        }
    }

    double valortotal() {
        return precoPagar;
    }

    public static void main(String[] args) {
        SistemaEntregas freteEntregas = new SistemaEntregas("CARLOS", 0);
        freteEntregas.calcularFrete(5);
        System.out.println("cliente " + freteEntregas.Nome() + " valor " + freteEntregas.valortotal());

    }
}