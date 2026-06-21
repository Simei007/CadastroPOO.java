package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {

    private ArrayList<PessoaFisica> pessoas;

    public PessoaFisicaRepo() {
        pessoas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pf) {
        pessoas.add(pf);
    }

    public void alterar(PessoaFisica pf) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getId() == pf.getId()) {
                pessoas.set(i, pf);
                return;
            }
        }
    }

    public void excluir(int id) {
        pessoas.removeIf(p -> p.getId() == id);
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica p : pessoas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return pessoas;
    }

    public void persistir(String arquivo) throws Exception {
        ObjectOutputStream out =
                new ObjectOutputStream(
                        new FileOutputStream(arquivo));

        out.writeObject(pessoas);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String arquivo) throws Exception {
        ObjectInputStream in =
                new ObjectInputStream(
                        new FileInputStream(arquivo));

        pessoas = (ArrayList<PessoaFisica>) in.readObject();
        in.close();
    }
}
