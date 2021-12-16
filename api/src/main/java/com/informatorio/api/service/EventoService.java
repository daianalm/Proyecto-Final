package com.informatorio.api.service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.informatorio.api.dto.EventoDTO;
import com.informatorio.api.entity.Emprendimiento;
import com.informatorio.api.entity.Estado;
import com.informatorio.api.entity.Evento;
import com.informatorio.api.repository.EmprendimientoRepository;
import com.informatorio.api.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@Configuration
@EnableScheduling
public class EventoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final EventoRepository eventoRepository;
    @Autowired
    public EventoService(EmprendimientoRepository emprendimientoRepository,
                         EventoRepository eventoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.eventoRepository = eventoRepository;
    }
    public Evento guardar(Evento evento) {
        return eventoRepository.save(evento);
    }
    public Evento eliminar(Long id, Evento evento) {
        Evento eventoEliminado = eventoRepository.getById(id);
        eventoEliminado.setActivo(false);
        return eventoRepository.save(eventoEliminado);
    }
    public void actualizarEstado(Evento evento, LocalDate ahora) {
        if (evento.getFechaDecierre().isBefore(ahora)) { evento.setEstado(Estado.EN_CURSO); }
        if (evento.getFechaFinal().isBefore(ahora)) { evento.setEstado(Estado.FINALIZADO); }
    }
    public void actualizar() {
        List<Evento> eventos = eventoRepository.findAll();
        LocalDate ahora = LocalDate.now();
        eventos.stream().forEach(evento -> actualizarEstado(evento, ahora));
        eventoRepository.saveAll(eventos);
        System.out.println("Estado del evento actualizado.");
    }
    public Emprendimiento registrar(Long emprendimientoId, Long eventoId, EventoDTO eventoDTO) {
        Emprendimiento emprendimientoRegistrado = emprendimientoRepository.getById(emprendimientoId);
        Evento eventoRegistrado = eventoRepository.getById(eventoId);
        if (eventoRegistrado.getEstado() == Estado.ABIERTO) { emprendimientoRegistrado.addEvento(eventoRegistrado);
            System.out.println("Suscripcion correcta");
        } else if (eventoRegistrado.getEstado() == Estado.EN_CURSO) { 
            System.out.println("Tiempo de suscripcion finalizado");
        } else { System.out.println("Evento finalizado."); }
        return emprendimientoRepository.save(emprendimientoRegistrado);
    }
    public Optional<Evento> rankear(Long id) {
        return eventoRepository.findById(id);
    }
}