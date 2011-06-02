package general



import org.junit.*
import grails.test.mixin.*


@TestFor(OrganizacionController)
@Mock(Organizacion)
class OrganizacionControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/organizacion/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.organizacionInstanceList.size() == 0
        assert model.organizacionInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.organizacionInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.organizacionInstance != null
        assert view == '/organizacion/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/organizacion/show/1'
        assert controller.flash.message != null
        assert Organizacion.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/organizacion/list'


        def organizacion = new Organizacion()

        // TODO: populate domain properties

        assert organizacion.save() != null

        params.id = organizacion.id

        def model = controller.show()

        assert model.organizacionInstance == organizacion
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/organizacion/list'


        def organizacion = new Organizacion()

        // TODO: populate valid domain properties

        assert organizacion.save() != null

        params.id = organizacion.id

        def model = controller.edit()

        assert model.organizacionInstance == organizacion
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/organizacion/list'

        response.reset()


        def organizacion = new Organizacion()

        // TODO: populate valid domain properties

        assert organizacion.save() != null

        // test invalid parameters in update
        params.id = organizacion.id

        controller.update()

        assert view == "/organizacion/edit"
        assert model.organizacionInstance != null

        organizacion.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/organizacion/show/$organizacion.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/organizacion/list'

        response.reset()

        def organizacion = new Organizacion()

        // TODO: populate valid domain properties
        assert organizacion.save() != null
        assert Organizacion.count() == 1

        params.id = organizacion.id

        controller.delete()

        assert Organizacion.count() == 0
        assert Organizacion.get(organizacion.id) == null
        assert response.redirectedUrl == '/organizacion/list'


    }


}