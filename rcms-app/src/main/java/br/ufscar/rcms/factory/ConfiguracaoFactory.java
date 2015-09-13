package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoSistema;
import br.ufscar.rcms.modelo.entidades.Usuario;

public class ConfiguracaoFactory {

    public static Configuracao createConfiguracao(final Tipo tipo) {
        return createConfiguracao(tipo, null);
    }

    public static Configuracao createConfiguracao(final Tipo tipo, final Usuario usuario) {
        switch (tipo) {
        case ESTILO_ADMIN:
        case ESTILO_PORTAL:
        case IDIOMA:
        case NOME_GRUPO:
        case DESCRICAO_GRUPO:
        case LOGOTIPO:
        case IMPORTACAO_LATTES_AUTOMATICA:
            return new ConfiguracaoSistema(tipo);

        case MICROSERVICE_AMOUNT_PRODUCAO_BY_RESEARCHER:
            return new ConfiguracaoSistema(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_RESEARCHER,
                    "http://localhost:23081/api.rcms/scorecard/producoes/amount/researcher");

        case MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR:
            return new ConfiguracaoSistema(Tipo.MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR,
                    "http://localhost:23081/api.rcms/scorecard/producoes/amount/year");

        default:
            throw new IllegalArgumentException("Invalid argument: " + tipo);
        }
    }
}