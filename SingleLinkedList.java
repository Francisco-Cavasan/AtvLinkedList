package LinkedList;

public class SingleLinkedList {

    private No inicio;
    private int tamanho;

    static class No {

        String valor;
        No proximo;

        No(String valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }
    
    public SingleLinkedList(){
        this.inicio = null;
        this.tamanho = 0;
    }

    public SingleLinkedList add(String valor) {
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

    public SingleLinkedList deleteValor(String valor) {

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
    public SingleLinkedList deletePosicao(int index) {

        No atual = this.inicio;
        No anterior = null;
//Se o index for 0, a posição inicial será deletada
        if (index == 0 && atual != null) {
            this.inicio = atual.proximo;
            //inicio passa a ser o proximo
            System.out.println("O valor na posição: " + index + " foi encontrado e deletado");
            return this;
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

}
