package general



import org.junit.*
import grails.test.mixin.*


@TestFor(UsuarioController)
@Mock(Usuario)
class UsuarioControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/usuario/lista" == response.redirectedUrl
    }

}
