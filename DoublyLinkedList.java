package LinkedList;

public class DoublyLinkedList {

    private No inicio;
    private int tamanho;

    static class No {

        String valor;
        No proximo;
        No anterior;

        No(String valor) {
            this.valor = valor;
        }
    }
    
    public DoublyLinkedList(){
        this.inicio = null;
        this.tamanho = 0;
    }
    
    public int getTamanho(){
        return this.tamanho;
    }
    
    public No getAtual(){
        No atual = this.inicio;
        for (int i = 1; atual != null && i < this.tamanho; i++) {
            atual = atual.proximo;
        }
        return atual;
    }

    public void addInicio(String valor) {
        No novoNo = new No(valor);

        //proximo do novo No é o inicio e o anterior é nulo porque não existe
        novoNo.proximo = this.inicio;
        novoNo.anterior = null;
        //muda o anterior do inicio para o novo No
        if (this.inicio != null) {
            this.inicio.anterior = novoNo;
        }
        tamanho++;
        this.inicio = novoNo;
    }
    
    public void addFinal(String valor){
        //cria um novo no com o valor passado na função
        No novoNo = new No(valor);
        No ultimo = this.inicio; 
        novoNo.proximo = null;
        //se a lista estiver vazia, o novo elemento é o inicio
        if (this.inicio == null) {
            novoNo.anterior = null;
            this.inicio = novoNo;
            tamanho++;
            return;
        }
        //passa por toda a lista até o último elemento
        while (ultimo.proximo != null){
            ultimo = ultimo.proximo;
        }
            
       //muda o valor do proximo do ultimo para o novo No
        ultimo.proximo = novoNo;
 
       //faz o anterior do novo No como o elemento que era o ultimo anteriormente
        novoNo.anterior = ultimo;
        tamanho++;
    }

    public void addBefore(No proximoNo, String valor) {
        //confirma que o No informado não é nulo
        if (proximoNo == null) {
            System.out.println("O nó informado não pode ser nulo");
            return;
        }
        No novoNo = new No(valor);

        //faz o proximo do novo ser o anterior do proximo
        novoNo.proximo = proximoNo.anterior;
        //anterior do proximo é o novo No criado
        proximoNo.anterior = novoNo;
        //proximo do novo é o proximo No
        novoNo.proximo = proximoNo;

        //verifica se o anterior do novo não é nulo e se nao for adiciona o
        // novo No criado nessa posição
        if (novoNo.anterior != null) {
            novoNo.anterior.proximo = novoNo;
        } else {
            this.inicio = novoNo;
        }
        tamanho++;
    }

    public void addAfter(No anteriorNo, String valor) {
        //confirma que o No informado não é nulo
        if (anteriorNo == null) {
            System.out.println("O nó informado não pode ser nulo");
            return;
        }
        No novoNo = new No(valor);

        //faz o proximo do novo ser o proximo do anterior
        novoNo.proximo = anteriorNo.proximo;
        //proximo do anterior é o novo No criado
        anteriorNo.proximo = novoNo;
        //anterior do novo é o proximo No
        novoNo.anterior = anteriorNo;

        //verifica se o proximo do novo não é nulo e se nao for adiciona o
        // novo No criado nessa posição
        if (novoNo.proximo != null) {
            novoNo.proximo.anterior = novoNo;
        }
        tamanho++;
    }

    public DoublyLinkedList deleteValor(String valor) {

        No atual = this.inicio;
        No anterior = null;
        No procurado = new No(valor);

        //caso o inicio tenha o valor a ser deletado, ele passa a ser o proximo
        if (atual != null && atual.valor == valor) {
            this.inicio = atual.proximo;
            tamanho--;
            System.out.println("O valor: " + valor + ", foi encontrado e deletado");
            return this;
        }

        //caso não seja a primeira posição, percorre até encontrar
        if (procurado.proximo != null) {
            procurado.proximo.anterior = procurado.anterior;
        }
        //caso a posição não for nula e o valor for igual, o anterior é o proximo do deletado
        if (procurado.anterior != null) {
            procurado.anterior.proximo = procurado.proximo;
        }

        //caso não encontrar o valor
        if (atual == null) {
            System.out.println("Valor não encontrado");
            return this;
        }
        tamanho--;
        return this;
    }

    public void deletePosicao(int index) {
        No atual = this.inicio;

        //passa por todas as posições até a posição desejada
        for (int i = 1; atual != null && i < index; i++) {
            atual = atual.proximo;
        }
//caso a lista esteja vazia
        if (atual == null) {
            return;
        }
        //aplica a função para deletar a posição
        deleteValor(atual.valor);
    }

    public void printList() {
        No inicio = this.inicio;
        while (inicio != null) {
            System.out.println(inicio.valor + " ");
            inicio = inicio.proximo;
        }
        System.out.println("\n");
    }

}
