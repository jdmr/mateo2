package general



import org.junit.*
import grails.test.mixin.*


@TestFor(OrganizacionController)
@Mock(Organizacion)
class OrganizacionControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/organizacion/lista" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.lista()

        assert model.organizaciones.size() == 0
        assert model.totalDeOrganizaciones == 0

    }

}
