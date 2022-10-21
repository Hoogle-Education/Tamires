import file.ExtraiDados;
import model.Mochila;
import model.Produto;

import java.io.IOException;
import java.util.List;

public class Main {

    public static Mochila geraMochila(String filename) {

        ExtraiDados extrator = new ExtraiDados();
        Mochila mochila = new Mochila(-1, -1);

        try {
            extrator.abrirArquivo(filename);
            extrator.extraiProdutos(mochila);
        } catch (Exception e) {
            System.out.println("Erro ao manipular o arquivo");
            System.out.println(e.getMessage());
        }

        try{
            extrator.fecharArquivo();
        } catch (Exception e) {
            System.out.println("Erro ao fechar o arquivo");
            System.out.println(e.getMessage());
        }

        return mochila;
    }

    public static void main(String[] args) {

        Mochila mochila = geraMochila("KNAPDATA40.txt");

        List<Produto> melhor = mochila.melhorMochila();

        System.out.println("--------------------------------");
        System.out.println(melhor.size());
        melhor.forEach(System.out::println);

    }

}