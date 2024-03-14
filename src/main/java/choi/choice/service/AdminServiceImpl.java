package choi.choice.service;

import choi.choice.domain.StdCtgry;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    @Override
    public List<StdCtgry> stdCtgryList() {
        return null;
    }
}
