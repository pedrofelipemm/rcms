package br.ufscar.rcms.dao;


public class FormacaoAcadmicaDAOTest extends AbstractDAOTestBase {
/*
    @Autowired
    private FormacaoAcademicaDAO formacaoAcademicaDAO;

    @Before
    public void init(){

        Endereco endereco = new Endereco();
        endereco.setEnderecoProfissional("Teste");
        Pesquisador pesquisador = new PesquisadorBuilder("login", "nome", "senha", "codigoLattes", "email", true, "resumoProfissional").endereco(endereco).build();
        salvar(pesquisador);

        FormacaoAcademica formacaoAcademica = FormacaoAcademicaFactory.createFormacaoAcademica(1L, 2000, 2000, "descricao", "nomeInstituicao",
                "tipo", pesquisador);

        FormacaoAcademica formacaoAcademica2 = FormacaoAcademicaFactory.createFormacaoAcademica(2L, 2000, 2000, "descricao", "nomeInstituicao",
                "tipo", pesquisador);

        pesquisador.setFormacoes(Arrays.asList(formacaoAcademica, formacaoAcademica2));

        // merge(formacaoAcademica, formacaoAcademica2);
        // merge(pesquisador);
        // merge(formacaoAcademica);
        // merge(formacaoAcademica2);
    }

    @Test
    public void buscarFormacaoAcademicaPorPesquisadorTest() {

        List<FormacaoAcademica> formacoes = formacaoAcademicaDAO.buscarFormacaoAcademica(1L);
        assertEquals(2, formacoes.size());

        FormacaoAcademica item1 = formacoes.get(0);

        assertEquals(Integer.valueOf(2000), item1.getAnoConclusao());
        assertEquals(Integer.valueOf(2000), item1.getAnoInicio());
        assertEquals("descricao", item1.getDescricao());
        assertEquals(Long.valueOf(1), item1.getIdFormacaoAcademica());
        assertEquals("nomeInstituicao", item1.getNomeInstituicao());
        assertEquals("tipo", item1.getTipo());
        assertEquals(Long.valueOf(1), item1.getPesquisador().getIdUsuario());
    }
*/
}