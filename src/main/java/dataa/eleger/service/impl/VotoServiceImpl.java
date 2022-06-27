package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.entidades.ItensDoVoto;
import dataa.eleger.entidades.VotoEntidade;
import dataa.eleger.modelos.voto.VotoDtoRequisicao;
import dataa.eleger.modelos.voto.VotoDtoResposta;
import dataa.eleger.repositorios.*;
import dataa.eleger.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class VotoServiceImpl implements VotoService {

    private final static Logger LOGGER   = Logger.getLogger(VotoServiceImpl.class.getName());
    private final VotoRepositorio votoRepositorio;
    private final EleicaoRepositorio eleicaoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final CandidatoRepositorio candidatoRepositorio;
    private final ItensDoVotoRepositorio itensDoVotoRepositorio;

    @Autowired
    public VotoServiceImpl(VotoRepositorio votoRepositorio, EleicaoRepositorio eleicaoRepositorio, UsuarioRepositorio usuarioRepositorio, CandidatoRepositorio candidatoRepositorio, ItensDoVotoRepositorio itensDoVotoRepositorio) {
        this.votoRepositorio = votoRepositorio;
        this.eleicaoRepositorio = eleicaoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.candidatoRepositorio = candidatoRepositorio;
        this.itensDoVotoRepositorio = itensDoVotoRepositorio;
    }

    @Override
    public VotoDtoResposta novoVoto(VotoDtoRequisicao votoDtoRequisicao) {
        return new VotoDtoResposta(
                votoRepositorio.save(votoDtoRequisicao.novoVoto(eleicaoRepositorio, usuarioRepositorio)));
    }

    @Override
    public List<VotoDtoResposta> ListarTodosOsVotos() {
        List<VotoEntidade> lista = (List<VotoEntidade>) votoRepositorio.findAll();
        return lista.stream().map(VotoDtoResposta::new).collect(Collectors.toList());
    }

    @Override
    public VotoDtoResposta salvaCandidatoVotado(Long voto, Long candidato) {
        VotoEntidade votoEntidade = buscarVoto(voto);
        CandidatoEntidade candidatoEntidade = buscarCandidato(candidato);
        List<CandidatoEntidade> candidatosPossiveis = votoEntidade.getEleicaoEntidade().getCandidato();

        if (candidatosPossiveis.contains(candidatoEntidade)) {
            List<ItensDoVoto> itens = votoEntidade.getItensDoVoto();
            ItensDoVoto salvarVoto = new ItensDoVoto(candidatoEntidade);
            itens.add(itensDoVotoRepositorio.save(salvarVoto));
            votoEntidade.setItensDoVoto(itens);
            return new VotoDtoResposta(votoRepositorio.save(votoEntidade));
        }
        return null;
    }



    public VotoEntidade buscarVoto(Long voto) throws NaoEncontrado {
        Optional<VotoEntidade> votoEntidade = votoRepositorio.findById(voto);
        return votoEntidade.orElseThrow(() -> new NaoEncontrado("Sinto muito... mas não encontrei o voto com id: " + voto));
    }

    public CandidatoEntidade buscarCandidato(Long candidato) throws NaoEncontrado{
        return candidatoRepositorio.findById(candidato).orElseThrow(() -> new NaoEncontrado("Não encontrei esse candidato"));
    }

}
