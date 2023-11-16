package projeto.teste;

import java.io.*;
import java.util.Scanner;

public class ContaBancaria {
    private Cliente cliente;
    private double saldo;
    private boolean ativa;

    public ContaBancaria(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.ativa = true;
    }

   
    public void depositar(double valor) {
        if (ativa) {
            if (valor > 0) {
                saldo += valor;
                System.out.println("Dep�sito de R$" + valor + " realizado para " + cliente.getNome() +
                        ". Novo saldo: R$" + saldo);
            } else {
                System.out.println("Valor inv�lido para dep�sito.");
            }
        } else {
            System.out.println("Conta desativada. N�o � poss�vel realizar dep�sitos.");
        }
    }

    public void sacar(double valor) {
        if (ativa) {
            if (valor > 0) {
                if (valor <= saldo) {
                    saldo -= valor;
                    System.out.println("Saque de R$" + valor + " realizado para " + cliente.getNome() +
                            ". Novo saldo: R$" + saldo);
                } else {
                    System.out.println("Saldo insuficiente para saque.");
                }
            } else {
                System.out.println("Valor inv�lido para saque.");
            }
        } else {
            System.out.println("Conta desativada. N�o � poss�vel realizar saques.");
        }
    }

    public void transferir(ContaBancaria contaDestino, double valor) {
        if (ativa) {
            if (valor > 0) {
                if (valor <= saldo) {
                    saldo -= valor;
                    contaDestino.depositar(valor);
                    System.out.println("Transfer�ncia de R$" + valor + " realizada para " +
                            contaDestino.cliente.getNome() + ". Novo saldo: R$" + saldo);
                } else {
                    System.out.println("Saldo insuficiente para transfer�ncia.");
                }
            } else {
                System.out.println("Valor inv�lido para transfer�ncia.");
            }
        } else {
            System.out.println("Conta desativada. N�o � poss�vel realizar transfer�ncias.");
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void ativarConta() {
        ativa = true;
        System.out.println("Conta ativada para " + cliente.getNome());
    }

    public void desativarConta() {
        ativa = false;
        System.out.println("Conta desativada para " + cliente.getNome());
    }
}
   
