package LinkedList;

public class main {

    public static void main(String[] args) {

        //Lista de a fazeres para uma mecanica
        SingleLinkedList single = new SingleLinkedList();
        //adiciona duas tarefas na lista
        single.add("Reparar para choque do fusca preto");
        single.add("Comprar peças para HRV branca com teto vermelho");
        single.printList();
        //Remove o valor no inicio da lista
        single.deletePosicao(0);
        single.printList();
        single.add("Trocar farol esquerdo Palio branco");
        single.add("Reparar amassado traseiro Gol azul escuro");
        single.printList();
        single.deleteValor("Trocar farol esquerdo Palio branco");
        single.printList();

        System.out.println("----------");

        //Sistema de historico de paginas de um navegador, permitindo avançar e voltar
        DoublyLinkedList doubly = new DoublyLinkedList();
        //abre na pagina inicial
        doubly.addInicio("pagina inicial");
        doubly.addAfter(doubly.getAtual(), "segunda pagina");
        doubly.addBefore(doubly.getAtual(), "terceira pagina");
        //adiciona um novo elemento antes do terceiro
        doubly.addBefore(doubly.getAtual(), "quarta pagina antes da teceira");
        doubly.printList();
        doubly.addFinal("quinta pagina");
        doubly.printList();

    }

}
