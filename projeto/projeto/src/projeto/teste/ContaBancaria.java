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
                System.out.println("Depósito de R$" + valor + " realizado para " + cliente.getNome() +
                        ". Novo saldo: R$" + saldo);
            } else {
                System.out.println("Valor inválido para depósito.");
            }
        } else {
            System.out.println("Conta desativada. Não é possível realizar depósitos.");
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
                System.out.println("Valor inválido para saque.");
            }
        } else {
            System.out.println("Conta desativada. Não é possível realizar saques.");
        }
    }

    public void transferir(ContaBancaria contaDestino, double valor) {
        if (ativa) {
            if (valor > 0) {
                if (valor <= saldo) {
                    saldo -= valor;
                    contaDestino.depositar(valor);
                    System.out.println("Transferência de R$" + valor + " realizada para " +
                            contaDestino.cliente.getNome() + ". Novo saldo: R$" + saldo);
                } else {
                    System.out.println("Saldo insuficiente para transferência.");
                }
            } else {
                System.out.println("Valor inválido para transferência.");
            }
        } else {
            System.out.println("Conta desativada. Não é possível realizar transferências.");
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
   
