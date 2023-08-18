package br.com.fiap.moneywrench.model;

public class Categoria { 
    
    private String nome;
    private String icone;
    
    public Categoria() {
    }

    public Categoria(String nome, String icone) {
        this.nome = nome;
        this.icone = icone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((icone == null) ? 0 : icone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Categoria other = (Categoria) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (icone == null) {
            if (other.icone != null)
                return false;
        } else if (!icone.equals(other.icone))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Categoria [nome=" + nome + ", icone=" + icone + "]";
    }

}
