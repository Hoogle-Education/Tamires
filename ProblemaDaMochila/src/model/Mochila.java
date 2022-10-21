package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mochila {

    private Integer[][] dp;
    private int quantidade;
    private int capacidade;
    private List<Produto> produtos;

    public Mochila(int quantidade, int capacidade) {
        this.quantidade = quantidade;
        this.capacidade = capacidade;
        produtos = new ArrayList<>();
        dp = new Integer[capacidade + 10][quantidade + 10];

    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionar(Produto produto) {
        // validação
        if(produtos.size() == quantidade) {
            System.out.println("Não foi possível adicionar o produto: " + produto);
            return;
        }
        produtos.add(produto);
    }

    public List<Produto> melhorMochila() {
        List<Produto> melhorCombinacao = new ArrayList<>();
        List<List<Produto>> combinacoes = new ArrayList<>();
        melhorMochila(combinacoes, melhorCombinacao, this.capacidade, 0);

        int maior = -1;

        for(List<Produto> combinacao : combinacoes) {
            if(valorCombinacao(combinacao) > maior) {
                melhorCombinacao = combinacao;
                maior = valorCombinacao(combinacao);
            }
        }

        return melhorCombinacao;
    }

    private void melhorMochila(List<List<Produto>> combinacoes, List<Produto> melhorCombinacao, int capacidade, int index) {
        // caso base

//        System.out.println("Index = " + index);
//        System.out.print("combinacao: ");

        melhorCombinacao.forEach(p -> System.out.print(p + " "));
        System.out.println();

        if(capacidade == 0 || index == quantidade)
            return;

        Produto produto = produtos.get(index);

        List<Produto> produtoFora = new ArrayList<>(melhorCombinacao); // nao coloco
        melhorMochila(combinacoes, produtoFora, capacidade, index+1);


        if(capacidade >= produto.getPeso()){
            List<Produto> produtosDentro = new ArrayList<>(melhorCombinacao);
            produtosDentro.add(produto);

            melhorMochila(combinacoes, produtosDentro, capacidade - produto.getPeso(), index+1);

            if(valorCombinacao(produtoFora) > valorCombinacao(produtosDentro)){
                melhorCombinacao = new ArrayList<>(produtoFora);
                combinacoes.add(produtosDentro);
            } else{
                melhorCombinacao = new ArrayList<>(produtosDentro);
                combinacoes.add(produtoFora);
            }
        } else {
            melhorCombinacao = new ArrayList<>(produtoFora);
            combinacoes.add(produtoFora);
        }

    }

    private int valorCombinacao(List<Produto> combinacao) {
        int soma = 0;
        for (Produto produto: combinacao) {
            soma += produto.getValor();
        }

        return soma;
    }

    @Override
    public String toString() {
        return "Mochila{" +
                "quantidade=" + quantidade +
                ", capacidade=" + capacidade +
                ", produtos=" + produtos +
                '}';
    }
}
