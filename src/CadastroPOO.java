import java.util.Scanner;

import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

PessoaFisicaRepo repoPF = new PessoaFisicaRepo();
PessoaJuridicaRepo repoPJ = new PessoaJuridicaRepo();

int opcao;

do {

    System.out.println("\n================================");
    System.out.println("1 - Incluir Pessoa");
    System.out.println("2 - Alterar Pessoa");
    System.out.println("3 - Excluir Pessoa");
    System.out.println("4 - Buscar pelo Id");
    System.out.println("5 - Exibir Todos");
    System.out.println("6 - Persistir Dados");
    System.out.println("7 - Recuperar Dados");
    System.out.println("0 - Finalizar Programa");
    System.out.println("================================");

    System.out.print("Opcao: ");
    opcao = Integer.parseInt(sc.nextLine());

    switch (opcao) {

        case 1:

    System.out.print("Tipo (F/J): ");
    String tipo = sc.nextLine().toUpperCase();

    if (tipo.equals("F")) {

        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        repoPF.inserir(
                new PessoaFisica(id, nome, cpf, idade));

        System.out.println("Pessoa Fisica cadastrada!");

    } else if (tipo.equals("J")) {

        System.out.print("Id: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CNPJ: ");
        String cnpj = sc.nextLine();

        repoPJ.inserir(
                new PessoaJuridica(id, nome, cnpj));

        System.out.println("Pessoa Juridica cadastrada!");
    }

    break;

        case 2:

    System.out.print("Tipo (F/J): ");
    String tipoAlterar = sc.nextLine().toUpperCase();

    System.out.print("Id da pessoa: ");
    int idAlterar = Integer.parseInt(sc.nextLine());

    if (tipoAlterar.equals("F")) {

        PessoaFisica pf = repoPF.obter(idAlterar);

        if (pf != null) {

            System.out.print("Novo Nome: ");
            String nome = sc.nextLine();

            System.out.print("Novo CPF: ");
            String cpf = sc.nextLine();

            System.out.print("Nova Idade: ");
            int idade = Integer.parseInt(sc.nextLine());

            repoPF.alterar(
                new PessoaFisica(idAlterar, nome, cpf, idade)
            );

            System.out.println("Pessoa Fisica alterada!");

        } else {
            System.out.println("Pessoa Fisica nao encontrada.");
        }

    } else if (tipoAlterar.equals("J")) {

        PessoaJuridica pj = repoPJ.obter(idAlterar);

        if (pj != null) {

            System.out.print("Novo Nome: ");
            String nome = sc.nextLine();

            System.out.print("Novo CNPJ: ");
            String cnpj = sc.nextLine();

            repoPJ.alterar(
                new PessoaJuridica(idAlterar, nome, cnpj)
            );

            System.out.println("Pessoa Juridica alterada!");

        } else {
            System.out.println("Pessoa Juridica nao encontrada.");
        }
    }

    break;

        case 3:

    System.out.print("Tipo (F/J): ");
    String tipoExcluir = sc.nextLine().toUpperCase();

    System.out.print("Id: ");
    int idExcluir = Integer.parseInt(sc.nextLine());

    if (tipoExcluir.equals("F")) {

        repoPF.excluir(idExcluir);
        System.out.println("Pessoa Fisica excluida.");

    } else if (tipoExcluir.equals("J")) {

        repoPJ.excluir(idExcluir);
        System.out.println("Pessoa Juridica excluida.");
    }

    break;

        case 4:

    System.out.print("Tipo (F/J): ");
    String tipoBusca = sc.nextLine().toUpperCase();

    System.out.print("Id: ");
    int idBusca = Integer.parseInt(sc.nextLine());

    if (tipoBusca.equals("F")) {

        PessoaFisica pf = repoPF.obter(idBusca);

        if (pf != null) {
            pf.exibir();
        } else {
            System.out.println("Pessoa Fisica nao encontrada.");
        }

    } else if (tipoBusca.equals("J")) {

        PessoaJuridica pj = repoPJ.obter(idBusca);

        if (pj != null) {
            pj.exibir();
        } else {
            System.out.println("Pessoa Juridica nao encontrada.");
        }
    }

    break;

        case 5:

    System.out.print("Tipo (F/J): ");
    String tipoLista = sc.nextLine().toUpperCase();

    if (tipoLista.equals("F")) {

        if (repoPF.quantidade() == 0) {
            System.out.println("Nenhuma Pessoa Fisica cadastrada.");
        } else {
            for (PessoaFisica pf : repoPF.obterTodos()) {
                pf.exibir();
                System.out.println();
            }
        }

    } else if (tipoLista.equals("J")) {

        if (repoPJ.quantidade() == 0) {
            System.out.println("Nenhuma Pessoa Juridica cadastrada.");
        } else {
            for (PessoaJuridica pj : repoPJ.obterTodos()) {
                pj.exibir();
                System.out.println();
            }
        }
    }

    break;

        case 6:

    try {

        System.out.print("Prefixo dos arquivos: ");
        String prefixo = sc.nextLine();

        repoPF.persistir(prefixo + ".fisica.bin");
        repoPJ.persistir(prefixo + ".juridica.bin");

        System.out.println("Dados gravados com sucesso!");

    } catch (Exception e) {

        System.out.println("Erro: " + e.getMessage());
    }

    break;

        case 7:

    try {

        System.out.print("Prefixo dos arquivos: ");
        String prefixo = sc.nextLine();

        repoPF.recuperar(prefixo + ".fisica.bin");
        repoPJ.recuperar(prefixo + ".juridica.bin");

        System.out.println("Dados recuperados com sucesso!");

    } catch (Exception e) {

        System.out.println("Erro: " + e.getMessage());
    }

    break;

        case 0:
            System.out.println("Programa Finalizado.");
            break;

        default:
            System.out.println("Opcao invalida.");
    }

} while (opcao != 0);

sc.close();
       }
}
    
