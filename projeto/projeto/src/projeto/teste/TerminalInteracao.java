             package projeto.teste;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import projeto.teste.Cliente;

public class TerminalInteracao {
    private static Map<String, ContaBancaria> contasCadastradas = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int escolha;
        do {
            exibirMenuInicial();
            escolha = scanner.nextInt();
            scanner.nextLine();  
            switch (escolha) {
                case 1:
                    
                    ContaBancaria novaConta = fazerCadastro(scanner);
                    contasCadastradas.put(novaConta.getCliente().getCpf(), novaConta);
                    break;
                case 2:
                   
                    ContaBancaria contaLogada = fazerLogin(scanner);
                    if (contaLogada != null) {
                        interagirComConta(contaLogada, scanner);
                    }
                    break;
                case 3:
                	    System.out.println("\nContas Bancárias Cadastradas:");

                	    for (ContaBancaria conta : contasCadastradas.values()) {
                	        System.out.println("CPF: " + conta.getCliente().getCpf() + ", Nome: " + conta.getCliente().getNome());
                	    }

                	    System.out.println();               
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);

        scanner.close();
    }

    private static void exibirMenuInicial() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Cadastrar Nova Conta Bancária");
        System.out.println("2 - Fazer Login em uma Conta Existente");
        System.out.println("3 - Exibir Contas Cadastradas");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }


    private static ContaBancaria fazerCadastro(Scanner scanner) {
        System.out.println("Cadastrando nova conta bancária.");

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        String cpf;
        boolean cpfExistente;
        do {
            System.out.print("Digite seu CPF: ");
            cpf = scanner.nextLine();

           
            cpfExistente = contasCadastradas.containsKey(cpf);
            if (cpfExistente) {
                System.out.println("CPF já cadastrado em outra conta. Por favor, escolha outro CPF.");
            }
        } while (cpfExistente);

        Cliente novoCliente = new Cliente(nome, cpf);
        ContaBancaria novaConta = new ContaBancaria(novoCliente);
        novaConta.ativarConta();  

        System.out.println("Conta criada para " + novoCliente.getNome());

        return novaConta;
    }


    private static ContaBancaria fazerLogin(Scanner scanner) {
        System.out.print("Digite o CPF para login: ");
        String cpf = scanner.nextLine();

        ContaBancaria contaLogada = contasCadastradas.get(cpf);

        if (contaLogada == null) {
            System.out.println("Conta não encontrada para o CPF informado.");
        }

        return contaLogada;
    }

    private static void interagirComConta(ContaBancaria conta, Scanner scanner) {
        System.out.println("Bem-vindo, " + conta.getCliente().getNome() + "!");

        int escolha;
        do {
            exibirMenuOperacoes();
            escolha = scanner.nextInt();
            scanner.nextLine();  

            switch (escolha) {
                case 1:
                    System.out.print("Digite o valor a depositar: ");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito);
                    break;
                case 2:
                    System.out.print("Digite o valor a sacar: ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    break;
                case 3:
                    System.out.print("Digite o CPF da conta destino: ");
                    String cpfDestino = scanner.nextLine();
                    ContaBancaria contaDestino = contasCadastradas.get(cpfDestino);
                    if (contaDestino != null) {
                        System.out.print("Digite o valor a transferir: ");
                        double valorTransferencia = scanner.nextDouble();
                        conta.transferir(contaDestino, valorTransferencia);
                    } else {
                        System.out.println("Conta destino não encontrada.");
                    }
                    break;
                case 4:
                    conta.ativarConta();
                    break;
                case 5:
                    conta.desativarConta();
                    break;
                case 6:
                    System.out.println("Saldo atual: R$" + conta.getSaldo());
                    break;
                    
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);
    }

    private static void exibirMenuOperacoes() {
        System.out.println("\nEscolha uma operação:");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Transferir");
        System.out.println("4 - Ativar Conta");
        System.out.println("5 - Desativar Conta");
        System.out.println("6 - Consultar Saldo");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }
}