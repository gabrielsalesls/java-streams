package io.github.gabrielsalesls;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Pessoa> pessoas = getPessoas();

        /*// IMPERATIVO ❌

        List<Pessoa> mulheres = new ArrayList<>();

        for(Pessoa pessoa : pessoas){
            if(pessoa.getGenero().equals(Genero.FEMININO)) {
                mulheres.add(pessoa);
            }
        }

        for (Pessoa pessoa : mulheres) {
            System.out.println(pessoa);
        }

        mulheres.forEach(System.out::println);*/

        // Funcional
        // Filter
        List<Pessoa> mulheress = pessoas.stream()
                .filter(pessoa -> pessoa.getGenero().equals(Genero.FEMININO))
                .collect(Collectors.toList());
        //mulheress.forEach(System.out::println);

        // Sort
        List<Pessoa> pessoasEmOrdemDeIdade = pessoas.stream()
                .sorted(Comparator.comparing(Pessoa::getIdade).thenComparing(Pessoa::getGenero).reversed())
                .collect(Collectors.toList());

//        pessoasEmOrdemDeIdade.forEach(System.out::println);


        // All Match
        boolean todasAsPessoasTemMaisDoQueCincoAnos = pessoas.stream()
                .allMatch(pessoa -> pessoa.getIdade() > 5);
        // System.out.println(todasAsPessoasTemMaisDoQueCincoAnos);

        // Any Match
        boolean algumaPessoaTemMaisDoQueQuarentaAnos = pessoas.stream()
                .anyMatch(pessoa -> pessoa.getIdade() > 40);
        //System.out.println(algumaPessoaTemMaisDoQueQuarentaAnos);

        // None Match
        boolean ninguemSeChamaAntonio = pessoas.stream()
                .noneMatch(pessoa -> pessoa.getNome().equals("Antonio"));
        //System.out.println(algumaPessoaTemMaisDoQueQuarentaAnos);
        //System.out.println(ninguemSeChamaAntonio);

        // Max
        pessoas.stream()
                .max(Comparator.comparing(Pessoa::getIdade));
//                .ifPresent(System.out::println);

        // Min
        pessoas.stream()
                .min(Comparator.comparing(Pessoa::getIdade));
//                .ifPresent(System.out::println);
        // Group
        Map<Genero, List<Pessoa>> agrupadoPorGenero = pessoas.stream()
                .collect(Collectors.groupingBy(Pessoa::getGenero));

/*        agrupadoPorGenero.forEach(((genero, pessoas1) -> {
            System.out.println(genero);
            pessoas1.forEach(System.out::println);
            System.out.println();
        }));*/

        Optional<String> nomeDaMulherMaisVelha = pessoas.stream()
                .filter(pessoa -> pessoa.getGenero().equals(Genero.FEMININO))
                .max(Comparator.comparing(Pessoa::getIdade))
                .map(Pessoa::getNome);

        nomeDaMulherMaisVelha.ifPresent(System.out::println);

        List<Integer> idades = pessoas.stream()
                .sorted(Comparator.comparing(Pessoa::getIdade).reversed())
                .map(pessoa -> pessoa.getIdade())
                .collect(Collectors.toList());

        idades.forEach(System.out::println);

    }

    private static List<Pessoa> getPessoas() {
        return List.of(
                new Pessoa("Chales Oliveira", 32, Genero.MASCULINO),
                new Pessoa("Ronda Rousey", 34, Genero.FEMININO),
                new Pessoa("Holly Holm", 40, Genero.FEMININO),
                new Pessoa("Robson Conceição", 33, Genero.MASCULINO),
                new Pessoa("Glover Teixeira", 42, Genero.MASCULINO),
                new Pessoa("Rose Namajunas", 29, Genero.FEMININO),
                new Pessoa("Amanda Nunes", 33, Genero.FEMININO)
        );
    }
}
