package general



import org.junit.*
import grails.test.mixin.*


@TestFor(EmpresaController)
@Mock(Empresa)
class EmpresaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/empresa/lista" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.lista()

        assert model.empresas.size() == 0
        assert model.totalDeEmpresas == 0

    }

}
