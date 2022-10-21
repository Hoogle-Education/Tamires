package file;

import model.Mochila;
import model.Produto;

import java.io.*;

public class ExtraiDados {

    private BufferedReader reader;

    public void abrirArquivo(String filename) throws FileNotFoundException {
        File file = new File("");
        String path = file.getAbsolutePath() + "\\" + filename;
        System.out.println(path);
        reader = new BufferedReader(new FileReader(path));
    }

    public void extraiProdutos(Mochila mochila) throws IOException {
        int capacidade = Integer.parseInt(reader.readLine());
        int quantidade = Integer.parseInt(reader.readLine());

        mochila.setCapacidade(capacidade);
        mochila.setQuantidade(quantidade);

        String linha;

        while( (linha = reader.readLine()) != null ){
            String[] dados = linha.split(",");
            Produto novoProduto = new Produto(dados[0],
                    Integer.parseInt(dados[1]), Integer.parseInt(dados[2]));
            mochila.adicionar(novoProduto);
        }
    }

    public void fecharArquivo() throws IOException {
        reader.close();
    }

}
