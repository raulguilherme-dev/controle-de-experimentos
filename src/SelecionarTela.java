import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelecionarTela extends JFrame {

    public SelecionarTela() throws Exception {
        final DefaultTableModel modelo = new DefaultTableModel();

        JTable tabela = new JTable(modelo);

        modelo.addColumn("Id");
        modelo.addColumn("Data Emissão Protocolo");
        modelo.addColumn("Data Inicio Experimento");
        modelo.addColumn("Data Fim Experimento");
        modelo.addColumn("Justificativa Uso Animais");
        modelo.addColumn("Resumo Portugues");
        modelo.addColumn("Resumo Inglês");
        modelo.addColumn("Data Envio Parecer");
        modelo.addColumn("Data Emissão Parecer");
        modelo.addColumn("Descrição Parecer");
        modelo.addColumn("Status Parecer");
        modelo.addColumn("Data Deliberação Protocolo");
        modelo.addColumn("Status Deliberação");
        modelo.addColumn("Status Protocolo");

        String sql = "select * from protocolo;";

        Connection conn = Conexao.ConexaoMySQL();
        PreparedStatement psts = conn.prepareStatement(sql);
        ResultSet rs = psts.executeQuery();

        Protocolo p = new Protocolo();
        List<Protocolo> pr = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("ID");
            String dataEmissaoProtocolo = rs.getString("dataEmissaoProtocolo");
            String dataInicioExperimento = rs.getString("dataInicioExperimento");
            String dataFimExperimento = rs.getString("dataFimExperimento");
            String justificativaUsoAnimais = rs.getString("justificativaUsoAnimais");
            String resumoPortugues = rs.getString("resumoPortugues");
            String resumoIngles = rs.getString("resumoIngles");
            String dataEnvioParecer = rs.getString("dataEnvioParecer");
            String dataEmissaoParecer = rs.getString("dataEmissaoParecer");
            String descrissaoParecer = rs.getString("descrissaoParecer");
            String statusParecer = rs.getString("statusParecer");
            String dataDeliberacaoProtocolo = rs.getString("dataDeliberacaoProtocolo");
            String statusDeliberacao = rs.getString("statusDeliberacao");
            String statusProtocolo = rs.getString("statusProtocolo");
            modelo.addRow(new Object[] {
                    id,
                    dataEmissaoProtocolo,
                    dataInicioExperimento,
                    dataFimExperimento,
                    justificativaUsoAnimais,
                    resumoPortugues,
                    resumoIngles,
                    dataEnvioParecer,
                    dataEmissaoParecer,
                    descrissaoParecer,
                    statusParecer,
                    dataDeliberacaoProtocolo,
                    statusDeliberacao,
                    statusProtocolo
            });

        }
        psts.execute();
        rs.close();
        psts.close();

        tabela.setPreferredScrollableViewportSize(new Dimension(1200, 700));

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JScrollPane scrollPane = new JScrollPane(tabela);
        c.add(scrollPane);

        this.setVisible(true);
        this.setSize(1280, 720);

    }


}
