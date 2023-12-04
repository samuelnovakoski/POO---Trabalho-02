public interface Estatistica{
    //atributo constante de faces dos dados
    final short numFaces = 6;

    //metodo para somar as faces sorteadas de cada dado (é implementado em JogoDados)
    public void somarFacesSorteadas(Dado[] dados);
}
