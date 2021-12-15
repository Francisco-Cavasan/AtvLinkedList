package LinkedList;

import java.util.Random;

public class LinkedListSort {

    private No inicio;
    private int tamanho;

    static class No {

        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public LinkedListSort() {
        this.inicio = null;
        this.tamanho = 0;
    }

    public void bubbleSort() {
        if (tamanho > 1) {
            boolean mudado;

            do {
                No atual = this.inicio;
                No anterior = null;
                No proximo = atual.proximo;
                mudado = false;

                while (proximo != null) {
                    if (atual.valor > proximo.valor) {
                        mudado = true;

                        if (anterior != null) {
                            No temp = proximo.proximo;

                            anterior.proximo = proximo;
                            proximo.proximo = atual;
                            atual.proximo = temp;
                        } else {
                            No temp = proximo.proximo;

                            inicio = proximo;
                            proximo.proximo = atual;
                            atual.proximo = temp;
                        }

                        anterior = proximo;
                        proximo = atual.proximo;
                    } else {
                        anterior = atual;
                        atual = proximo;
                        proximo = proximo.proximo;
                    }
                }
            } while (mudado);
        }
    }

    private No getMeio(No inicio) {
        if (inicio == null) {
            return inicio;
        }
        //guarda uma variavel apontando para o inicio da lista
        No lento = inicio;
        //outra variavel para o proximo do primeiro
        No rapido = inicio.proximo;
        //executa o loop enquanto não chegar no final da lista
        while (rapido != null && rapido.proximo != null) {
            //avança a variavel lento para o elemento seguinte
            lento = lento.proximo;
            rapido = rapido.proximo.proximo;
        }
        // nesse ponto a variavel lento estará apontando para o meio da lista
        return lento;
    }

    private No merge(No a, No b) {
        No merged = null;
        //testes para saber se alguma parte está vazia
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        //escolhe entre a parte da esqueda ou da direita
        if (a.valor <= b.valor) {
            merged = a;
            //aplica a mesma funcao de forma recursiva
            merged.proximo = merge(a.proximo, b);
        } else {
            merged = b;
            merged.proximo = merge(a, b.proximo);
        }
        return merged;
    }

    public No mergeSort(No inicio) {
        if (inicio.proximo == null || inicio == null) {
            return inicio;
        }

        //pega o elemento do meio da lista
        No meio = getMeio(inicio);
        No proximoMeio = meio.proximo;
        //o meio se torna nulo
        meio.proximo = null;
        //organiza a lista da esqueda
        No listaEsquerda = mergeSort(inicio);
        No listaDireita = mergeSort(proximoMeio);
        //aplica a funcao para juntar as duas partes
        No merged = merge(listaEsquerda, listaDireita);
        //retorna a lista ordenada
        return merged;
    }

    public LinkedListSort add(int valor) {
        No novoNo = new No(valor);
        novoNo.proximo = null;
        //caso a lista esteja vazia, é colocado na primeira posição
        if (this.inicio == null) {
            this.inicio = novoNo;
        } //caso contrario percorre a lista até encontrar uma posição nula
        else {
            No ultimo = this.inicio;
            while (ultimo.proximo != null) {
                ultimo = ultimo.proximo;
            }
            ultimo.proximo = novoNo;
        }
        this.tamanho++;
        return this;
    }

    public LinkedListSort deleteValor(int valor) {

        No atual = this.inicio;
        No anterior = null;

        //caso o inicio tenha o valor a ser deletado, ele passa a ser o proximo
        if (atual != null && atual.valor == valor) {
            this.inicio = atual.proximo;
            System.out.println("O valor: " + valor + ", foi encontrado e deletado");
        }

        //caso não seja a primeira posição, percorre até encontrar
        while (atual != null && atual.valor != valor) {
            anterior = atual;
            atual = atual.proximo;
        }
        //caso a posição não for nula e o valor for igual, o anterior é o proximo do deletado
        if (atual != null) {
            anterior.proximo = atual.proximo;
            System.out.println("O valor: " + valor + ", foi encontrado e deletado");
        }

        //caso não encontrar o valor
        if (atual == null) {
            System.out.println("Valor não encontrado");
            return null;
        }
        this.tamanho--;
        return this;
    }

    //Delete o elemento na posição informada
    public LinkedListSort deletePosicao(int index) {

        No atual = this.inicio;
        No anterior = null;
//Se o index for 0, a posição inicial será deletada
        if (index == 0 && atual != null) {
            this.inicio = atual.proximo;
            //inicio passa a ser o proximo
            System.out.println("O valor na posição: " + index + " foi encontrado e deletado");
        }

        int count = 0;
//Contador para saber quando chega na posição a ser deletada
        while (atual != null) {
            if (count == index) {
                //quando a posicao for encontrada, o anterior passa a ser o proximo do que foi deletado
                anterior.proximo = atual.proximo;
                System.out.println("O valor na posição: " + index + " foi encontrado e deletado");
                break;
            } else {
                //se nao é a posição a ser deletada, continua percorrendo a lista
                anterior = atual;
                atual = atual.proximo;
                count++;
            }
        }
        //caso a posição não tenha sido encontrada
        if (atual == null) {
            System.out.println("O valor na posição: " + index + " não foi encontrado");
            return null;
        }
        this.tamanho--;
        return this;
    }

    public void printList() {
        No atual = this.inicio;

        while (atual != null) {
            System.out.println(atual.valor + "");
            atual = atual.proximo;
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Random random = new Random();
        LinkedListSort bubble = new LinkedListSort();
        for (int i = 0; i < 10000; i++) {
            int rand = random.nextInt();
            bubble.add(rand);
        }
        long start1 = System.nanoTime();
        bubble.bubbleSort();
        long end1 = System.nanoTime();
        System.out.println("Temp em nano segundos do bubble sort: " + (end1 - start1));
        LinkedListSort merge = new LinkedListSort();

        for (int i = 0; i < 10000; i++) {
            int rand = random.nextInt();
            merge.add(rand);
        }
        long start2 = System.nanoTime();
        merge.inicio = merge.mergeSort(merge.inicio);
        long end2 = System.nanoTime();
        System.out.println("Temp em nano segundos do marge sort: " + (end2 - start2));

    }

}
