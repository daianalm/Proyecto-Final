package com.informatorio.api.converter;
import java.time.LocalDateTime;
import com.informatorio.api.dto.VotoDTO;
import com.informatorio.api.entity.Voto;
import com.informatorio.api.repository.EmprendimientoRepository;
import com.informatorio.api.repository.UsuarioRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VotoDTOVotoConverter implements Converter<VotoDTO, Voto> {
    public VotoDTOVotoConverter(EmprendimientoRepository emprendimientoRepository,
                                  UsuarioRepository usuarioRepository) {
    }
    @Override
    public Voto convert(VotoDTO votoDto) {
        Voto voto = new Voto();
        voto.setGenerado(votoDto.getGenerado());
        voto.setUsuarioId(votoDto.getUsuarioId());
        voto.setEmprendimientoId(votoDto.getEmprendimientoId());
        voto.setFechaDeCreacion(LocalDateTime.now());
        return voto;
    } 
}
