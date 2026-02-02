import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

public class ContaBancaria {
    private double saldo;
    private String nome;
    private int Idade;
    private String CPF;

    ContaBancaria(String n, int i, double s, String c) {
        this.nome = n;
        this.Idade = i;
        this.saldo = s;
        this.CPF = c;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return Idade;
    }

    public String getcpf() {
        return CPF;
    }

    public double getSaldo() {
        return saldo;
    }

    void dados() {
        System.out.println(" SEUS DADOS ");
        System.out.println();
        System.out.println("NOME: " + this.nome);
        System.out.println("IDADE: " + this.Idade);
        System.out.println("CPF: " + this.CPF);
    }

    void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depositado o valor de " + valor + " em sua conta bancaria!");
        } else {
            System.out.println("VALOR INVALIDO ");
        }
    }

    void sacar(double Valor) {
        if (Valor <= saldo) {
            saldo -= Valor;
            System.out.println("Saque realizado com sucesso no valor de " + Valor + " reais!");
        } else {
            System.out.println("Saldo insuficiente ! ");
        }
    }

    public static void upload(ArrayList<ContaBancaria> lista) {
        try {

            FileWriter arquivo = new FileWriter("BancoDados.text");
            PrintWriter escritor = new PrintWriter(arquivo);

            for (ContaBancaria c : lista) {
                escritor.println(c.getNome() + "; " + c.Idade + "; " + c.getSaldo() + "; " + c.getcpf() + ".");
            }

            arquivo.close();
            escritor.close();
            System.out.println("ARQUIVO SALVO COM SUCESSO  ");
        }

        catch (Exception e) {
            System.out.println("ERRO AO SALVAR ARQUIVOS " + e.getMessage());

        }

    }

    public static void carregarDados(ArrayList<ContaBancaria> lista) {
        try {
            File arquivo = new File("BancoDados.text");
            if (!arquivo.exists())
                return;

            Scanner leitorArquivo = new Scanner(arquivo);
            while (leitorArquivo.hasNextLine()) {
                String linha = leitorArquivo.nextLine();
                String[] partes = linha.split(";");

                String nome = partes[0];
                int idade = Integer.parseInt(partes[1]);
                double saldo = Double.parseDouble(partes[2]);
                String cpf = partes[3];

                lista.add(new ContaBancaria(nome, idade, saldo, cpf));
            }
            leitorArquivo.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }
    }

    public void Transferir(ContaBancaria para, double valor) {
        if (this.saldo >= valor) {
            this.sacar(valor);
            para.depositar(valor);
            System.out.println("Transferencia realizada com sucesso!");
        } else {
            System.out.println("VALOR INSUFICIENTE");
        }
    }

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
       ArrayList<ContaBancaria> conta = new ArrayList<>();

        carregarDados(conta);
        if (conta.isEmpty()) {
             conta.add(new ContaBancaria("Admin", 99, 0, "000"));
        }
        boolean sistemaLigado = true;

        while (sistemaLigado) {
            System.out.println("\n=== BANCO INFINITO ===");
            System.out.println("1 - Entrar");
            System.out.println("2 - Cadastrar (Nova Conta)");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            int opcao = leitor.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Digite seu nome de usuario!");
                    String nomeLoguin = leitor.next();

                    ContaBancaria logada = null;

                    for (ContaBancaria c : conta) {
                        if (c.getNome().equals(nomeLoguin)) {
                            logada = c;
                            break;
                        }
                    }

                    if (logada != null) {
                        System.out.println("Bem vindo, " + logada.getNome());
                        boolean menuCliente = true;

                        while (menuCliente) {
                            System.out.println(" ----   AREA DE " + logada.getNome() + " ---");
                            System.out.println("1 - Depositar");
                            System.out.println("2 - Sacar");
                            System.out.println("3 - Transferir");
                            System.out.println("4 - Ver Saldo");
                            System.out.println("0 - Sair (Deslogar)");
                            System.out.print("Opção: ");
                            int escolha = leitor.nextInt();

                            switch (escolha) {
                                case 1:
                                    System.out.print("Valor para depositar: ");
                                    logada.depositar(leitor.nextDouble());
                                    break;

                                case 2:
                                    System.out.print("Valor para sacar: ");
                                    logada.sacar(leitor.nextDouble());
                                    break;

                                case 3:
                                    System.out.print("Para quem enviar? (Nome): ");
                                    String nomeDestino = leitor.next();

                                    ContaBancaria destino = null;
                                    for (ContaBancaria c : conta) {
                                        if (c.getNome().equals(nomeDestino)) {
                                            destino = c;
                                            break;
                                        }
                                    }

                                    if (destino != null) {
                                        System.out.print("Valor do PIX: ");
                                        double valorPix = leitor.nextDouble();
                                        logada.Transferir(destino, valorPix);
                                    } else {
                                        System.out.println("Destinatário não encontrado.");
                                    }
                                    break;

                                case 4:
                                    System.out.println("SEU SALDO: R$ " + logada.getSaldo());
                                    break;

                                case 0:
                                    System.out.println("ENCERRANDO O SISTEMA");
                                    menuCliente = false;
                                    break;

                                default:
                                    System.out.println("Opção inválida");
                            }
                        }
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;

                case 2:
                    System.out.println("CADASTRAMENTO DE CONTA! ");
                    System.out.print("QUAL O SEU PRIMEIRO NOME? ");
                    String nome = leitor.next();
                    System.out.print("Qual a sua idade? ");
                    int idade = leitor.nextInt();
                    System.out.print("DIGITE OS TRES PRIMEIROS NUMEROS DO SEU CPF! ");
                    String CPF = leitor.next();

                    conta.add(new ContaBancaria(nome, idade, 0, CPF));

                    System.out.println("\nUsuario cadastrado com sucesso");
                    System.out.println("Seja bem vindo " + nome + "!");
                    if (idade < 18) {
                        System.out.println("ALERTA: MENOR DE 18 ANOS! ");
                    }
                    break;

                case 0:
                    System.out.println("ENCERRANDO O SISTEMA ");
                    System.out.println("SALVANDO DADOS ");
                    upload(conta);
                    System.out.println("Dados salvos");
                    System.out.println();
                    sistemaLigado = false;
                    break;

                default:
                    System.out.println("Opção invalida ");
                    break;
            }
        }
    }
}