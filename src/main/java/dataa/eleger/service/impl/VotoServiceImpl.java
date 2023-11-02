package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.Exceptions.ViolacaoDeRegra;
import dataa.eleger.entidades.*;
import dataa.eleger.modelos.itensDeVoto.ItensDeVotoDtoRequisicao;
import dataa.eleger.modelos.itensDeVoto.ItensDeVotoDtoResposta;
import dataa.eleger.modelos.voto.VotoDtoRequisicao;
import dataa.eleger.modelos.voto.VotoDtoResposta;
import dataa.eleger.repositorios.*;
import dataa.eleger.service.VotoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepositorio votoRepositorio;
    private final EleicaoRepositorio eleicaoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final CandidatoRepositorio candidatoRepositorio;
    private final ItensDoVotoRepositorio itensDoVotoRepositorio;

    public VotoServiceImpl(VotoRepositorio votoRepositorio, EleicaoRepositorio eleicaoRepositorio, UsuarioRepositorio usuarioRepositorio, CandidatoRepositorio candidatoRepositorio, ItensDoVotoRepositorio itensDoVotoRepositorio) {
        this.votoRepositorio = votoRepositorio;
        this.eleicaoRepositorio = eleicaoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.candidatoRepositorio = candidatoRepositorio;
        this.itensDoVotoRepositorio = itensDoVotoRepositorio;
    }

    @Override
    public VotoDtoResposta novoVoto(VotoDtoRequisicao votoDtoRequisicao) throws ViolacaoDeRegra {

        EleicaoEntidade eleicao = eleicaoRepositorio.findById(votoDtoRequisicao.getEleicao())
                .orElseThrow(() -> new NaoEncontrado("Nao encontrei o cadastro da Eleiçao." + votoDtoRequisicao.getEleicao()));

        if (eleicao.getInicio().isBefore(LocalDateTime.now())
                && eleicao.getFim().isAfter(LocalDateTime.now())) {

            usuarioRepositorio.findById(votoDtoRequisicao.getUsuario())
                    .orElseThrow(() -> new NaoEncontrado("Desculpe mas não encontrei o Usuário com id: " + votoDtoRequisicao.getUsuario()));

            List<VotoEntidade> seEleitorJaVotou = votoRepositorio.procurarPorEleicaoAndUsuario(votoDtoRequisicao.getEleicao(),
                    votoDtoRequisicao.getUsuario());

            if (seEleitorJaVotou.isEmpty()) {

                return new VotoDtoResposta(
                    votoRepositorio.save(votoDtoRequisicao.novoVoto(eleicaoRepositorio, usuarioRepositorio)));

            }
            throw new ViolacaoDeRegra("Já tenho o Voto deste Eleitor nesta Campanha!!!");
        }

        throw new ViolacaoDeRegra("Não consegui salvar... " +
                "Verifique se a Data do Voto estar entre a data inicial e final da Eleição.");

    }


    @Override
    public List<VotoDtoResposta> ListarTodosOsVotos() {
        List<VotoEntidade> lista = (List<VotoEntidade>) votoRepositorio.findAll();
        return lista.stream().map(VotoDtoResposta::new).collect(Collectors.toList());
    }

    @Override
    public ItensDeVotoDtoResposta salvaCandidatoVotado(ItensDeVotoDtoRequisicao voto) throws ViolacaoDeRegra {

        // verificar se o voto existe
        VotoEntidade votoEntidade = buscarVoto(voto.getVotoEntidade());
        System.out.println("voto ativo -> " + votoEntidade);
        // Ver todos os candidatos cadastrado nesta eleiçao
        List<CandidatoEntidade> candidatosPossiveis = votoEntidade.getEleicaoEntidade().getCandidato();

        System.out.println("candidatos para essa eleição -> " + candidatosPossiveis);

        System.out.println("quantidade de candidatos desta eleição -> " + candidatosPossiveis.size());

        // candidato a receber o voto.
        CandidatoEntidade candidatoEntidade = buscarCandidato(voto.getCandidatoEntidade());

        // candidatos já votados
        List<ItensDoVoto> itensDeVotos = votoEntidade.getItensDoVoto();

        // verificar se o candidato ou seu cargo ja foi votado.
        for(ItensDoVoto item : itensDeVotos) {
            if (item.getCandidatoEntidade().getCargoEntidade().getIdCargo()
                    .equals(candidatoEntidade.getCargoEntidade().getIdCargo()))
                throw new ViolacaoDeRegra("Ja tenho um candidato votado para este cargo");
            if (item.getCandidatoEntidade().getIdCandidato().equals(candidatoEntidade.getIdCandidato()))
                throw new ViolacaoDeRegra("Esse candidato ja foi Votado.");
        }

        //caso de tudo certo, Verificar se a quantidade de votos é compatível com os cargos cadastrados para essa eleição.
        List<String> cargosElegiveis = new ArrayList<>();

        for(CandidatoEntidade item : candidatosPossiveis) {
            cargosElegiveis.add(item.getCargoEntidade().getNomeCargo());
            System.out.println(cargosElegiveis);
        }

        int numeroDeCargos = cargosElegiveis.stream().distinct().collect(Collectors.toList()).size();
        System.out.println(numeroDeCargos);

        if (itensDeVotos.size() < numeroDeCargos) {
            return new ItensDeVotoDtoResposta(itensDoVotoRepositorio.save(voto.novoItemDoVoto(candidatoRepositorio, votoRepositorio)));
        }
        throw new ViolacaoDeRegra("Verifique a quantidade de Votos. temos " + numeroDeCargos + " Cargos e a mesma quantidade de votos!");
    }

    public VotoEntidade buscarVoto(Long voto) throws NaoEncontrado {
        Optional<VotoEntidade> votoEntidade = votoRepositorio.findById(voto);
        return votoEntidade.orElseThrow(() -> new NaoEncontrado("Sinto muito... mas não encontrei o voto com id: " + voto));
    }

    public CandidatoEntidade buscarCandidato(Long candidato) throws NaoEncontrado{
        return candidatoRepositorio.findById(candidato).orElseThrow(() -> new NaoEncontrado("Não encontrei esse candidato"));
    }

}
