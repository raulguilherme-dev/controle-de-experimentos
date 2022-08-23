import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

public class ProtocoloDAO {
    public void emitir(Protocolo p) throws Exception{
        String sql = "INSERT INTO protocolo (dataEmissaoProtocolo, dataInicioExperimento, "
                + "dataFimExperimento, justificativaUsoAnimais, resumoPortugues, resumoIngles, "
                + "statusParecer, statusProtocolo) "
                + "VALUES (?, ?, ?, ?, ?, ?, 'Aguardando Envio', 'Aguardando envio para o parecer');";

        Connection con = null;
        PreparedStatement psts = null;

        con = Conexao.ConexaoMySQL();
        psts = con.prepareStatement(sql);

        psts.setString(1, p.getDataEmissaoProtocolo());
        psts.setString(2, p.getDataInicioExperimento());
        psts.setString(3, p.getDataFimExperimento());
        psts.setString(4, p.getJustificativaUsoAnimais());
        psts.setString(5, p.getResumoPortugues());
        psts.setString(6, p.getResumoIngles());
        psts.execute();
    }

    public void enviarParecer(Protocolo p) throws Exception{
        String sql = "UPDATE protocolo set dataEnvioParecer = ?, statusParecer = 'Enviado', " +
                "statusProtocolo = 'Aguardando Parecer' where id = ?;";

        Connection con = null;
        PreparedStatement psts = null;

        con = Conexao.ConexaoMySQL();
        psts = con.prepareStatement(sql);

        psts.setString(1, LocalDate.now().toString());
        psts.setInt(2, p.getId());
        psts.execute();
    }

    public void emitirParecer(Protocolo p) throws Exception{
        String sql = "UPDATE protocolo set dataEmissaoParecer=?, " +
                "statusParecer = 'Emitido', statusProtocolo = 'Aguardando Deliberação', " +
                "descrissaoParecer = ? where id = ?;";

        Connection con = null;
        PreparedStatement psts = null;

        con = Conexao.ConexaoMySQL();
        psts = con.prepareStatement(sql);

        psts.setString(1, LocalDate.now().toString());
        psts.setString(2, p.getDescricaoParecer());
        psts.setInt(3, p.getId());
        psts.execute();
    }

    public void deliberarProtocolo(Protocolo p) throws Exception {
        String sql = "UPDATE protocolo set dataDeliberacaoProtocolo=?, " +
                "statusDeliberacao = 'Deliberado' where id = ?;";

        Connection con = null;
        PreparedStatement psts = null;

        con = Conexao.ConexaoMySQL();
        psts = con.prepareStatement(sql);

        psts.setString(1, LocalDate.now().toString());
        psts.setInt(2, p.getId());
        psts.execute();
    }
}