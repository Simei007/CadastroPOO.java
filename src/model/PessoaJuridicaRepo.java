package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {

    private ArrayList<PessoaJuridica> pessoas;

    public PessoaJuridicaRepo() {
        pessoas = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pj) {
        pessoas.add(pj);
    }

    public void alterar(PessoaJuridica pj) {
        for (int i = 0; i < pessoas.size(); i++) {
            if (pessoas.get(i).getId() == pj.getId()) {
                pessoas.set(i, pj);
                return;
            }
        }
    }

    public void excluir(int id) {
        pessoas.removeIf(p -> p.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica p : pessoas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<PessoaJuridica> obterTodos() {
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

        pessoas = (ArrayList<PessoaJuridica>) in.readObject();
        in.close();
    }
}
