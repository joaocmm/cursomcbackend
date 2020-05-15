
package com.cosmusti.cursomc.services;

import com.cosmusti.cursomc.domain.ItemPedido;
import com.cosmusti.cursomc.domain.PagamentoComBoleto;
import com.cosmusti.cursomc.domain.Pedido;
import com.cosmusti.cursomc.domain.enums.EstadoPagamento;
import com.cosmusti.cursomc.repositories.ItemPedidoRepository;
import com.cosmusti.cursomc.repositories.PagamentoRepository;
import com.cosmusti.cursomc.repositories.PedidoRepository;
import com.cosmusti.cursomc.services.exceptions.ObjectNotFoundException;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repository;
    
    @Autowired
    private BoletoService boletoService;
    
    @Autowired
    private PagamentoRepository pagamentoRepository;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    
    public Pedido buscarPorId(Long id){
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+ Pedido.class.getName()));
    }
    
    @Transactional
    public Pedido inserir(Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
        }
        obj = repository.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()){
            ip.setDesconto(0.0);
            ip.setPreco(produtoService.buscarPorId(ip.getProduto().getId()).getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }
    
}
