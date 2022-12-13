import java.util.Random;
import javax.swing.JOptionPane;

public class Jokenpo
{
    public static Random random = new Random();
    public static final int PEDRA = 1;
    public static final int PAPEL = 2;
    public static final int TESOURA = 3;
    public static final int DERROTA = 0;
    public static final int VITORIA = 1;
    public static final int EMPATE = 2;
    
    public static int jogadausuario = 0;
    private int jogadainimigo;

    public int getJogadainimigo() {
        return jogadainimigo;
    }

    public void setJogadainimigo(int jogadainimigo) {
        this.jogadainimigo = jogadainimigo;
    }

    public static int status;
    public static int pontosbot = 0, pontosjogador = 0;
    
    public static void titulo()
    {
        float versao;
        
        versao = 2.1f;
       

    }
    
    //Metodo onde o Menu principal se desenvolve
    public static void menu()
    {
        boolean erro;        
        
        do
        {
            erro = false;
            
            try
            {
                jogadausuario = Integer.parseInt(JOptionPane.showInputDialog(null, "[1] Pedra\n[2] Papel\n[3] Tesoura\n[0] Sair\nJogador1 " +pontosjogador +" VS Jogador2 " +pontosbot));
               
                duelar();
            }

            catch(Exception e)
            {
                erro = true;
                JOptionPane.showMessageDialog(null, "Erro: Entrada invalida, digite um numero inteiro");
            }
        } while(JOptionPane.showConfirmDialog(null, "Deseja continuar?") == JOptionPane.YES_OPTION || erro == true);
    }
    
    
    
    public static int duelar()
    {
        
        if(jogadausuario == Jokenpo.getInstance().getJogadainimigo())
        {
            if(jogadausuario == PEDRA)
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Pedra\nJogador2 jogou Pedra\nEMPATE");
            if(jogadausuario == PAPEL)
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Papel\nJogador2 jogou Papel\nEMPATE");
            if(jogadausuario == TESOURA)
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Tesoura\nJogador2 jogou Tesoura\nEMPATE");
            
            status = EMPATE;
            return EMPATE;
        }
        
        else
        {
            if(jogadausuario == PEDRA && Jokenpo.getInstance().getJogadainimigo() == PAPEL)
            {
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Pedra\nJogador2 jogou Papel\nJogador 2 Venceu! :(");
                status = DERROTA;
                pontosbot++;
                return DERROTA;
            }
            
            if(jogadausuario == PEDRA && Jokenpo.getInstance().getJogadainimigo() == TESOURA)
            {
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Pedra\nJogador2 jogou Tesoura\nJogador 1 Venceu! :)");
                status = VITORIA;
                pontosjogador++;
                return VITORIA;
            }
            
            if(jogadausuario == PAPEL && Jokenpo.getInstance().getJogadainimigo() == PEDRA)
            {
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Papel\nJogador2 jogou Pedra\nJogador 1 Venceu!:)");
                pontosjogador++;
                status = VITORIA;
                return VITORIA;
            }
            
            if(jogadausuario == PAPEL && Jokenpo.getInstance().getJogadainimigo() == TESOURA)
            {
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Papel\nJogador2 jogou Tesoura\nJogador 2 Venceu! :(");
                status = DERROTA;
                pontosbot++;
                return DERROTA;
            }
            
            if(jogadausuario == TESOURA && Jokenpo.getInstance().getJogadainimigo() == PAPEL)
            {
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Tesoura\nJogador2 jogou Papel\nJogador 1 Venceu!:)");
                status = VITORIA;
                pontosjogador++;
                return VITORIA;
            }
            
            if(jogadausuario == TESOURA && Jokenpo.getInstance().getJogadainimigo() == PEDRA)
            {
                JOptionPane.showMessageDialog(null, "Jogador1 jogou Tesoura\nJogador2 jogou Pedra\nJogador 2 Venceu! :(");
                status = DERROTA;
                pontosbot++;
                return DERROTA;
            }
        }
        
        return status;
    }

    private static Jokenpo instancia;

    private Jokenpo() {

    }

    public static synchronized Jokenpo getInstance() {
        if (instancia == null) {
            instancia = new Jokenpo();
        }
        return instancia;
    }
}